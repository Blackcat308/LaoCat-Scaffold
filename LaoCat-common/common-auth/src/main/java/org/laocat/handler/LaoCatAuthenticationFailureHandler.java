package org.laocat.handler;

import com.alibaba.fastjson2.JSON;
import org.laocat.core.response.structure.ResponseEntity;
import org.laocat.core.response.structure.ResponseEntityEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.laocat.constant.AuthConstant.CONTENT_TYPE;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description 失败处理器
 */
public class LaoCatAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType(CONTENT_TYPE);
        response.getWriter().write(JSON.toJSONString(ResponseEntity.fail(ResponseEntityEnum.PASSWORD_VERIFICATION_ERROR)));
    }
}
