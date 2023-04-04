package org.laocat.file.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


/**
 * @description: minio 上传请求类
 * @author: LaoCat
 * @date: 2023/4/4
 */
@Data
@ApiModel(value = "minio上传请求类")
public class MinioUploadReq {

    /**
     * 桶名称
     */
    @ApiModelProperty(required = true, value = "桶名称")
    private String bucketName;

    /**
     * 对象名称
     */
    @ApiModelProperty(required = true, value = "对象名称")
    private String objectName;

    /**
     * 文件
     */
    @ApiModelProperty(required = true, value = "文件")
    private MultipartFile file;

}
