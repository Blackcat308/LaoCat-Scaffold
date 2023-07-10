package org.laocat.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson2.JSON;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.laocat.core.response.structure.ResponseEntity;
import org.laocat.file.req.MinioRemoveReq;
import org.laocat.file.req.MinioUploadReq;
import org.laocat.minio.LaoCatMinio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @description: minio 文件服务 处理器
 * @author: LaoCat
 * @date: 2023/4/4
 */
@Api(tags = "minio文件服务")
@RestController
@ConditionalOnProperty(name = "file.handler", havingValue = "minio")
@RequestMapping("file/minio")
public class MinioFileHandler {

    private final LaoCatMinio minioTemplate;

    /**
     * 分钟
     */
    @Value("${minio.default-expiry}")
    private Integer defaultExpiry;

    @Autowired
    public MinioFileHandler(LaoCatMinio minio) {
        this.minioTemplate = minio;
    }

    /**
     * @description: 创建桶
     * @author: LaoCat
     * @date: 2023/4/4
     * @returnType: org.laocat.core.response.structure.ResponseEntity<java.lang.Object>
     */
    @PostMapping("bucket")
    @ApiOperation(value = "创建桶", notes = "创建桶")
    public ResponseEntity<String> createBucket(@RequestParam String bucketName) {
        minioTemplate.createBucket(bucketName);

        Optional<Bucket> addedBucket = minioTemplate.bucket(bucketName);
        return ResponseEntity.success(JSON.toJSONString(addedBucket.get()));
    }

    /**
     * @description: 获取所有的桶
     * @author: LaoCat
     * @date: 2023/4/4
     * @returnType: org.laocat.core.response.structure.ResponseEntity<java.lang.Object>
     */
    @GetMapping("buckets")
    @ApiOperation(value = "获取所有的桶", notes = "获取所有的桶")
    public ResponseEntity<String> buckets() {
        List<Bucket> buckets = minioTemplate.buckets();
        return ResponseEntity.success(JSON.toJSONString(buckets));
    }


    /**
     * @description: 删除桶
     * @author: LaoCat
     * @date: 2023/4/4
     * @returnType: org.laocat.core.response.structure.ResponseEntity<java.lang.Object>
     */
    @DeleteMapping("bucket")
    @ApiOperation(value = "删除桶", notes = "删除桶")
    public ResponseEntity<Object> removeBucket(@RequestParam String bucketName) {
        minioTemplate.deleteBucket(bucketName);
        return ResponseEntity.success();
    }


    /**
     * @description: 上传
     * @author: LaoCat
     * @date: 2023/4/4
     * @returnType: org.laocat.core.response.structure.ResponseEntity<java.lang.Object>
     */
    @PostMapping("object")
    @ApiOperation(value = "上传", notes = "上传")
    public ResponseEntity<Object> upload(MinioUploadReq uploadReq) {

        try {
            MultipartFile file = uploadReq.getFile();
            String filename = file.getOriginalFilename();

            String objectFileName =
                    String.join("",
                            uploadReq.getObjectName(),
                            IdUtil.objectId(),
                            Objects.requireNonNull(filename).substring(filename.lastIndexOf("."))
                    );

            minioTemplate.upload(
                    uploadReq.getBucketName(),
                    objectFileName,
                    file.getInputStream(),
                    file.getSize(),
                    file.getContentType()
            );

            return ResponseEntity.success(minioTemplate.getObjectUrl(
                    Method.GET,
                    uploadReq.getBucketName(),
                    objectFileName,
                    defaultExpiry,
                    TimeUnit.MINUTES
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.fail();
    }

    /**
     * @description: 文件对象批量删除
     * @author: LaoCat
     * @date: 2023/4/4
     * @returnType: void
     */
    @DeleteMapping("objects")
    @ApiOperation(value = "文件对象批量删除", notes = "文件对象批量删除")
    public ResponseEntity<Object> removeObject(@RequestBody MinioRemoveReq removeReq) {
        minioTemplate.deleteBatchObject(removeReq.getBucketName(), removeReq.getObjectName());

        return ResponseEntity.success();
    }

    /**
     * @description: 文件对象删除
     * @author: LaoCat
     * @date: 2023/4/4
     * @returnType: void
     */
    @DeleteMapping("object/{bucketName}/{objectName}")
    @ApiOperation(value = "文件对象批量删除", notes = "文件对象批量删除")
    public ResponseEntity<Object> removeObject(@PathVariable String bucketName, @PathVariable String objectName) {
        minioTemplate.deleteObject(bucketName, objectName);

        return ResponseEntity.success();
    }

    /**
     * @description: 下载
     * @author: LaoCat
     * @date: 2023/4/4
     * @returnType: void
     */
    @GetMapping("object")
    @ApiOperation(value = "下载", notes = "下载")
    public ResponseEntity<Object> download(@RequestParam String bucketName, @RequestParam String objectName,
                                           @RequestParam String fileName) {
        minioTemplate.downloadObject(bucketName, objectName, fileName);

        return ResponseEntity.success();
    }


}
