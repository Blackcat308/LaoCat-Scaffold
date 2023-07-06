package org.laocat.core.response.structure;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

import static org.laocat.core.response.structure.ResponseEntityEnum.FAIL;

/**
 * @author LaoCat
 * @date 2022/6/16
 * @description 参照 mall4cloud 项目返回结构
 */
@Data
@ToString
@ApiModel
public class ResponseEntity<T> implements Serializable {

    @ApiModelProperty(value = "返回code")
    private String code;
    @ApiModelProperty(value = "返回信息")
    private String message;
    @ApiModelProperty(value = "返回体")
    private T data;

    /**
     * @return org.laocat.core.response.structure.ResponseEntity<T>
     * @author LaoCat
     * @date 2022/6/16
     * @description 成功 有返回体
     */
    public static <T> ResponseEntity<T> success(T data) {
        ResponseEntity<T> serverResponseEntity = new ResponseEntity<>();
        serverResponseEntity.setData(data);
        serverResponseEntity.setCode(ResponseEntityEnum.OK.value());
        serverResponseEntity.setMessage(ResponseEntityEnum.OK.getMessage());
        return serverResponseEntity;
    }

    /**
     * @return org.laocat.core.response.structure.ResponseEntity<T>
     * @author LaoCat
     * @date 2022/6/16
     * @description 成功 无返回体
     */
    public static <T> ResponseEntity<T> success() {
        ResponseEntity<T> serverResponseEntity = new ResponseEntity<>();
        serverResponseEntity.setCode(ResponseEntityEnum.OK.value());
        serverResponseEntity.setMessage(ResponseEntityEnum.OK.getMessage());
        return serverResponseEntity;
    }

    /**
     * @return org.laocat.core.response.structure.ResponseEntity<T>
     * @author LaoCat
     * @date 2022/6/16
     * @description 失败 通用
     */
    public static <T> ResponseEntity<T> fail() {
        ResponseEntity<T> serverResponseEntity = new ResponseEntity<>();
        serverResponseEntity.setMessage(FAIL.value());
        serverResponseEntity.setCode(FAIL.getMessage());
        return serverResponseEntity;
    }

    /**
     * @return org.laocat.core.response.structure.ResponseEntity<T>
     * @author LaoCat
     * @date 2022/6/16
     * @description 失败 无返回体
     */
    public static <T> ResponseEntity<T> fail(ResponseEntityEnum responseEnum) {
        ResponseEntity<T> serverResponseEntity = new ResponseEntity<>();
        serverResponseEntity.setMessage(responseEnum.getMessage());
        serverResponseEntity.setCode(responseEnum.value());
        return serverResponseEntity;
    }

    /**
     * @return org.laocat.core.response.structure.ResponseEntity<T>
     * @author LaoCat
     * @date 2022/6/16
     * @description 失败 有返回体
     */
    public static <T> ResponseEntity<T> fail(ResponseEntityEnum responseEnum, T data) {
        ResponseEntity<T> serverResponseEntity = new ResponseEntity<>();
        serverResponseEntity.setMessage(responseEnum.getMessage());
        serverResponseEntity.setCode(responseEnum.value());
        serverResponseEntity.setData(data);
        return serverResponseEntity;
    }

    /**
     * @return org.laocat.core.response.structure.ResponseEntity<T>
     * @author LaoCat
     * @date 2022/6/16
     * @description 失败 异常专用
     */
    public static <T> ResponseEntity<T> fail(String errMsg) {
        ResponseEntity<T> serverResponseEntity = new ResponseEntity<>();
        serverResponseEntity.setMessage(errMsg);
        serverResponseEntity.setCode(FAIL.value());
        return serverResponseEntity;
    }

}
