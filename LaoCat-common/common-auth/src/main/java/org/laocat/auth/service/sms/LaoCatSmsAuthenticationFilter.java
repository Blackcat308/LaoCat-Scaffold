package org.laocat.auth.service.sms;

import cn.hutool.core.util.StrUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 短信路由拦截filter
 * @author: LaoCat
 * @date: 2023/7/11
 */
public class LaoCatSmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public final static String PHONE_PARAM = "phone";
    public final static String VERIFICATION_CODE = "verificationCode";
    private static final AntPathRequestMatcher SMS_PATH_REQUEST_MATCHER =
            new AntPathRequestMatcher("/smsLogin", "POST");

    public LaoCatSmsAuthenticationFilter() {
        super(SMS_PATH_REQUEST_MATCHER);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String phone = request.getParameter(PHONE_PARAM);
        String verificationCode = request.getParameter(VERIFICATION_CODE);
        // 手机号 验证码 都不能为空
        if (StrUtil.isBlank(phone) || StrUtil.isBlank(verificationCode)) {
            phone = "";
            verificationCode = "";
        }

        LaoCatSmsAuthenticationToken smsAuthenticationToken = new LaoCatSmsAuthenticationToken(phone, verificationCode);
        this.setDetails(request, smsAuthenticationToken);

        return getAuthenticationManager().authenticate(smsAuthenticationToken);
    }

    private void setDetails(HttpServletRequest request, LaoCatSmsAuthenticationToken smsAuthenticationToken) {
        smsAuthenticationToken.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}
