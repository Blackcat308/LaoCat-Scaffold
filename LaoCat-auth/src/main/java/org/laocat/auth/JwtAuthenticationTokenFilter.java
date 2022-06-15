package org.laocat.auth;

import cn.hutool.log.StaticLog;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.laocat.authorize.CoreAuthorizeConfigProvider;
import org.laocat.propertiess.SecurityProperties;
import org.laocat.utils.JwtUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description jwt过滤器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {


    public static final String HEADER_NAME = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private final JwtUtil jwtUtil;

    private final CoreAuthorizeConfigProvider coreAuthorizeConfigProvider;

    private final SecurityProperties securityProperties;

    private final StringRedisTemplate redisTemplate;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public JwtAuthenticationTokenFilter(JwtUtil jwtUtil, CoreAuthorizeConfigProvider coreAuthorizeConfigProvider, SecurityProperties securityProperties, StringRedisTemplate redisTemplate) {
        this.jwtUtil = jwtUtil;
        this.coreAuthorizeConfigProvider = coreAuthorizeConfigProvider;
        this.securityProperties = securityProperties;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        StaticLog.info("请求路径：{}，请求方式为：{}", request.getRequestURI(), request.getMethod());

        if (antPathMatcher.match("/favicon.ico", request.getRequestURI())) {
            StaticLog.info("jwt不拦截此路径：{}，请求方式为：{}", request.getRequestURI(), request.getMethod());
            filterChain.doFilter(request, response);
            return;
        }
        /*
         * get请求是否需要进行Authentication请求头校验，true：默认校验；false：不拦截GET请求
         * 因为get请求比较特殊
         */
        if (!securityProperties.isDischargedGetMethod()) {
            if (Objects.equals(RequestMethod.GET.toString(), request.getMethod())) {
                StaticLog.info("jwt不拦截此路径因为开启了不拦截GET请求：{}，请求方式为：{}", request.getRequestURI(), request.getMethod());
                filterChain.doFilter(request, response);
                return;
            }
        }

        /*
         * 排除路径，并且如果是options请求是cors跨域预请求，设置allow对应头信息
         * 这里的 getPermitUrls 是在 @com.fulinlin.authorize.Provider.CoreAuthorizeConfigProvider 中设置的
         */
        String[] permitUrls = getPermitUrls();
        for (String permitUrl : permitUrls) {
            if (antPathMatcher.match(permitUrl, request.getRequestURI())
                    || Objects.equals(RequestMethod.OPTIONS.toString(), request.getMethod())) {
                StaticLog.info("jwt不拦截此路径：{}，请求方式为：{}", request.getRequestURI(), request.getMethod());
                filterChain.doFilter(request, response);
                return;
            }
        }


        // 获取Authorization
        String authHeader = request.getHeader(HEADER_NAME);
        if (StringUtils.isBlank(authHeader) || !authHeader.startsWith(TOKEN_PREFIX)) {
            StaticLog.error("Authorization的开头不是Bearer，Authorization===>{}", authHeader);
            responseEntity(response, HttpStatus.UNAUTHORIZED.value(), "暂无权限！");
            return;
        }

        // 截取token
        String authToken = authHeader.substring(TOKEN_PREFIX.length());

        /*
         * 判断token是否失效
         */
        if (jwtUtil.isTokenExpired(authToken)) {
            StaticLog.info("token已过期！");
            responseEntity(response, HttpStatus.UNAUTHORIZED.value(), "token已过期！");
            return;
        }

        String randomKey = jwtUtil.getMd5KeyByToken(authToken);
        String username = jwtUtil.getUsernameByToken(authToken);

        /*
         * 验证token是否合法
         */
        if (StringUtils.isBlank(username) || StringUtils.isBlank(randomKey)) {
            StaticLog.info("username{}或randomKey{} 可能为null！", username, randomKey);
            responseEntity(response, HttpStatus.UNAUTHORIZED.value(), "暂无权限！");
            return;
        }

        /*
         *验证token是否存在（过期了也会消失）
         */
        Object token = redisTemplate.opsForValue().get(JwtRedisEnum.getTokenKey(username, randomKey));
        if (Objects.isNull(token)) {
            StaticLog.info("Redis里没查到key{}对应的value！", JwtRedisEnum.getTokenKey(username, randomKey));
            responseEntity(response, HttpStatus.UNAUTHORIZED.value(), "token已过期！");
            return;
        }

        /*
         * 判断传来的token和存到redis的token是否一致
         */
        if (!Objects.equals(token.toString(), authToken)) {
            StaticLog.error("前端传来的token{}和redis里的token{}不一致！", authToken, token.toString());
            responseEntity(response, HttpStatus.UNAUTHORIZED.value(), "暂无权限！");
            return;
        }

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            String authenticationStr = redisTemplate.opsForValue().get(JwtRedisEnum.getAuthenticationKey(username, randomKey));
            Authentication authentication = JSON.parseObject(authenticationStr, Authentication.class);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // token过期时间
        long tokenExpireTime = jwtUtil.getExpirationDateByToken(authToken).getTime();

        // token还剩余多少时间过期
        long surplusExpireTime = (tokenExpireTime - System.currentTimeMillis()) / 1000;
        StaticLog.info("Token剩余时间:" + surplusExpireTime);

        /*
         * 退出登录不刷新token，因为假设退出登录操作，刷新token了，这样清除的是旧的token，相当于根本没退出成功
         */
        if (!StringUtils.equals(request.getRequestURL(), JwtUrlEnum.LOGOUT.url())) {
            // token过期时间小于等于多少秒，自动刷新token
            if (surplusExpireTime <= securityProperties.getAutoRefreshRemainingTime()) {
                // 1.删除之前的token
                redisTemplate.delete(JwtRedisEnum.getTokenKey(username, randomKey));
                //2.重新生成token
                //3.重新生成randomKey，放到header以及redis
                String newRandomKey = jwtUtil.getRandomKey();
                // 重新生成token，放到header以及redis
                String newAuthToken = jwtUtil.refreshToken(authToken, newRandomKey);
                response.setHeader(HEADER_NAME, TOKEN_PREFIX + newAuthToken);
                response.setHeader("randomKey", newRandomKey);
                redisTemplate.opsForValue().set(JwtRedisEnum.getTokenKey(username, newRandomKey),
                        newAuthToken,
                        securityProperties.getEffectiveTime(),
                        TimeUnit.SECONDS);
                StaticLog.info("重新生成token{}和randomKey{}", newAuthToken, newRandomKey);

                // 取出老的authenticate放到redis里
                String authentication = redisTemplate.opsForValue().get(JwtRedisEnum.getAuthenticationKey(username, randomKey));
                redisTemplate.opsForValue().set(JwtRedisEnum.getAuthenticationKey(username, newRandomKey),
                        authentication,
                        securityProperties.getEffectiveTime(),
                        TimeUnit.SECONDS);

                // 删除旧的认证信息,这里设置50s后自动到期，是因为在实际应用中有可能从authentication里获取用户唯一标识
                redisTemplate.opsForValue().set(
                        JwtRedisEnum.getAuthenticationKey(username, randomKey),
                        authentication,
                        50,
                        TimeUnit.SECONDS);
            }
        }

        filterChain.doFilter(request, response);
    }

    /**
     * 获取需要过滤的url
     *
     * @return
     */
    private String[] getPermitUrls() {
        /* 核心模块相关的URL */
        String[] corePermitUrls = coreAuthorizeConfigProvider.getPermitUrls();
        /* 返回的数组 */
        return ArrayUtils.addAll(corePermitUrls);
    }

    private void responseEntity(HttpServletResponse response, Integer status, String message) {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(status);
        ModelMap modelMap = GenerateModelMap.generateMap(status, message);
        try {
            response.getWriter().write(
                    JSON.toJSONString(modelMap));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
