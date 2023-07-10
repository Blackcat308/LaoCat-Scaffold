package org.laocat.controller;

import cn.hutool.core.util.IdUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.laocat.core.response.structure.ResponseEntity;
import org.laocat.qiniu.LaoCatQiuNiu;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

/**
 * @description: 七牛云 文件服务 处理器
 * @author: LaoCat
 * @date: 2023/4/4
 */
@Api(tags = "七牛云文件服务")
@RestController
@ConditionalOnProperty(name = "file.handler", havingValue = "qiniu")
@RequestMapping("file/qiniu")
public class QiNiuFileHandler {

    private final LaoCatQiuNiu qiNiuTemplate;

    public QiNiuFileHandler(LaoCatQiuNiu qiuNiu) {
        this.qiNiuTemplate = qiuNiu;
    }


    /**
     * @description: 上传
     * @author: LaoCat
     * @date: 2023/4/4
     * @returnType: org.laocat.core.response.structure.ResponseEntity<java.lang.Object>
     */
    @PostMapping
    @ApiOperation(value = "上传", notes = "上传")
    public ResponseEntity<Object> upload(MultipartFile file) {

        try {
            String filename = file.getOriginalFilename();

            qiNiuTemplate.upload(
                    String.join("",
                            IdUtil.objectId(), Objects.requireNonNull(filename).substring(filename.lastIndexOf("."))),
                    file.getInputStream());
            return ResponseEntity.success();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.fail();
    }

}
