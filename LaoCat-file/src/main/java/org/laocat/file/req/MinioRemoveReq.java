package org.laocat.file.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: @see
 * @author: LaoCat
 * @date: 2023/4/4
 */
@Data
@ApiModel(value = "minio删除请求类")
public class MinioRemoveReq {

    /**
     * 桶名称
     */
    @ApiModelProperty(required = true, value = "桶名称")
    private String bucketName;

    /**
     * 对象名称
     */
    @ApiModelProperty(required = true, value = "对象名称")
    private List<String> objectName;

}
