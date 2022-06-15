package org.laocat.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LaoCat
 * @date 2022/6/14
 * @description 测试网管跳转
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Value("test")
    private String test;


    @GetMapping("ping")
    public String heartbeat() {
        return "heartbeat success: " + test;
    }

}
