package org.laocat.core.config;

import cn.hutool.core.util.StrUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LaoCat
 * @date 2022/6/16
 * @description 实现feign请求携带 token
 */
@Component
public class LaoCatFeignInterceptor implements RequestInterceptor {

    /**
     * @author LaoCat
     * @date 2022/6/16
     * @description 对feign请求进行相关拦截 并传递请求头 授权并未覆盖其他模块。
     * @return void
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        if (attributes == null) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        String authorization = request.getHeader("Authorization");


        if (StrUtil.isNotBlank(authorization)) {
            requestTemplate.header("Authorization", authorization);
        }
    }
}
