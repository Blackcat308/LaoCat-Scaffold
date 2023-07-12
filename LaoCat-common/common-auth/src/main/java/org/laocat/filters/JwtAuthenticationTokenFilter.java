package org.laocat.filters;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.laocat.auth.JwtRedisEnum;
import org.laocat.auth.JwtUtil;
import org.laocat.auth.manager.CoreAuthorizeConfigProvider;
import org.laocat.config.SecurityProperties;
import org.laocat.core.response.structure.ResponseEntity;
import org.laocat.core.response.structure.ResponseEntityEnum;
import org.laocat.utils.RedisUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static org.laocat.constant.AuthConstant.*;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description jwt过滤器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    private final CoreAuthorizeConfigProvider coreAuthorizeConfigProvider;

    private final SecurityProperties securityProperties;

    private final RedisUtil redisUtil;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    public JwtAuthenticationTokenFilter(JwtUtil jwtUtil, CoreAuthorizeConfigProvider coreAuthorizeConfigProvider,
                                        SecurityProperties securityProperties, RedisUtil redisUtil) {
        this.jwtUtil = jwtUtil;
        this.coreAuthorizeConfigProvider = coreAuthorizeConfigProvider;
        this.securityProperties = securityProperties;
        this.redisUtil = redisUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /*
         * get请求是否需要进行Authentication请求头校验，true：默认校验；false：不拦截GET请求
         * 因为get请求比较特殊
         */
        if (!securityProperties.isDischargedGetMethod()) {
            if (Objects.equals(RequestMethod.GET.toString(), request.getMethod())) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        String[] permitUrls = getPermitUrls();
        for (String permitUrl : permitUrls) {
            if (antPathMatcher.match(permitUrl, request.getRequestURI())
                    || Objects.equals(RequestMethod.OPTIONS.toString(), request.getMethod())) {
                filterChain.doFilter(request, response);
                return;
            }
        }


        // 获取Authorization
        String authHeader = request.getHeader(AUTHORIZATION);
        if (StringUtils.isBlank(authHeader) || !authHeader.startsWith(BEARER)) {
            responseEntity(response, HttpStatus.UNAUTHORIZED.value(), ResponseEntity.fail(ResponseEntityEnum.UNAUTHORIZED));
            return;
        }

        // 截取token
        String authToken = authHeader.substring(BEARER.length());

        /*
         * 判断token是否失效
         */
        if (jwtUtil.isTokenExpired(authToken)) {
            responseEntity(response, HttpStatus.UNAUTHORIZED.value(), ResponseEntity.fail(ResponseEntityEnum.TOKEN_EXPIRED));
            return;
        }

        String randomKey = jwtUtil.getMd5KeyByToken(authToken);
        String username = jwtUtil.getUsernameByToken(authToken);

        /*
         * 验证token是否合法
         */
        if (StringUtils.isBlank(username) || StringUtils.isBlank(randomKey)) {
            responseEntity(response, HttpStatus.UNAUTHORIZED.value(), ResponseEntity.fail(ResponseEntityEnum.UNAUTHORIZED));
            return;
        }

        /*
         *验证token是否存在（过期了也会消失）
         */
        Object token = redisUtil.get(JwtRedisEnum.getTokenKey(username, randomKey));
        if (Objects.isNull(token)) {
            responseEntity(response, HttpStatus.UNAUTHORIZED.value(), ResponseEntity.fail(ResponseEntityEnum.TOKEN_EXPIRED));
            return;
        }

        /*
         * 判断传来的token和存到redis的token是否一致
         */
        if (!Objects.equals(token.toString(), authToken)) {
            responseEntity(response, HttpStatus.UNAUTHORIZED.value(), ResponseEntity.fail(ResponseEntityEnum.UNAUTHORIZED));
            return;
        }

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            Authentication authentication = null;
            Object authenticationObj = redisUtil.get(JwtRedisEnum.getAuthenticationKey(username, randomKey));
            if (Objects.nonNull(authenticationObj)) {
                authentication = (Authentication) authenticationObj;
            }
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // token过期时间
        long tokenExpireTime = jwtUtil.getExpirationDateByToken(authToken).getTime();

        // token还剩余多少时间过期
        long surplusExpireTime = (tokenExpireTime - System.currentTimeMillis()) / 1000;

        /*
         * 退出登录不刷新token，因为假设退出登录操作，刷新token了，这样清除的是旧的token，相当于根本没退出成功
         */
        if (!StringUtils.equals(request.getRequestURL(), LOGOUT_URL)) {
            // token过期时间小于等于多少秒，自动刷新token
            if (surplusExpireTime <= securityProperties.getAutoRefreshRemainingTime()) {
                // 1.删除之前的token
                redisUtil.del(JwtRedisEnum.getTokenKey(username, randomKey));
                //2.重新生成token
                //3.重新生成randomKey，放到header以及redis
                String newRandomKey = jwtUtil.getRandomKey();
                // 重新生成token，放到header以及redis
                String newAuthToken = jwtUtil.refreshToken(authToken, newRandomKey);
                response.setHeader(AUTHORIZATION, String.join(BEARER, newAuthToken));
                response.setHeader(RANDOM_KEY, newRandomKey);

                redisUtil.set(JwtRedisEnum.getTokenKey(username, newRandomKey), newAuthToken, securityProperties.getEffectiveTime());
                // 取出老的authenticate放到redis里
                Object authenticationObj = redisUtil.get(JwtRedisEnum.getAuthenticationKey(username, randomKey));

                assert authenticationObj != null;

                Authentication authentication = (Authentication) authenticationObj;
                redisUtil.set(JwtRedisEnum.getAuthenticationKey(username, newRandomKey), authentication, securityProperties.getEffectiveTime());

                // 删除旧的认证信息,这里设置50s后自动到期，是因为在实际应用中有可能从authentication里获取用户唯一标识
                redisUtil.set(JwtRedisEnum.getAuthenticationKey(username, randomKey), authentication, 50);
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

    private void responseEntity(HttpServletResponse response, int status, ResponseEntity<?> responseEntity) {
        response.setContentType(CONTENT_TYPE);
        response.setStatus(status);
        try {
            response.getWriter().write(
                    JSON.toJSONString(responseEntity));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
