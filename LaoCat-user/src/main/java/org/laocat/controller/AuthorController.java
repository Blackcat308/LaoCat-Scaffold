package org.laocat.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.laocat.auth.JwtUtil;
import org.laocat.core.response.structure.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LaoCat
 * @date 2022/6/16
 * @description
 */
@RestController
@AllArgsConstructor
@Api(tags = "获取jwt相关")
public class AuthorController {

    private final JwtUtil jwtUtil;

    @GetMapping("author")
    @ApiOperation(value = "获取jwt的payload部分",notes = "获取jwt的payload部分")
    public ResponseEntity<Object> author(@RequestParam String token) {
        return ResponseEntity.success(JSON.toJSON(jwtUtil.getClaimByToken(token)));
    }
}
