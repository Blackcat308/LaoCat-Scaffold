package org.laocat.handler;

import com.alibaba.fastjson.JSON;
import org.laocat.auth.GenerateModelMap;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.ui.ModelMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description 失败处理器
 */
public class LaoCatAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ModelMap modelMap = GenerateModelMap.generateMap(HttpStatus.INTERNAL_SERVER_ERROR.value(), "验证失败");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(modelMap));
    }
}
