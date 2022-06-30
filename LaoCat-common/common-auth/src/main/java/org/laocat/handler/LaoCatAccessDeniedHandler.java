package org.laocat.handler;

import com.alibaba.fastjson2.JSON;
import org.laocat.core.response.structure.ResponseEntity;
import org.laocat.core.response.structure.ResponseEntityEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.laocat.constant.AuthConstant.CONTENT_TYPE;

/**
 * @see AccessDeniedHandler
 * @description: 无权限返回处理器
 * @author: LaoCat
 * @date: 2022/6/24
 */
@Component
public class LaoCatAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setContentType(CONTENT_TYPE);
        response.getWriter().write(JSON.toJSONString(ResponseEntity.fail(ResponseEntityEnum.NO_PERMISSION)));
    }
}
