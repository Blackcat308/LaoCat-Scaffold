package org.laocat.core.response.structure;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author LaoCat
 * @date 2022/6/16
 * @description 参照 mall4cloud 项目返回结构
 */
@Data
@ToString
public class ResponseEntity<T> implements Serializable {

    private String code;

    private String message;

    private T data;

    /**
     * @author LaoCat
     * @date 2022/6/16
     * @description 成功 有返回体
     * @return org.laocat.core.response.structure.ResponseEntity<T>
     */
    public static <T> ResponseEntity<T> success(T data) {
        ResponseEntity<T> serverResponseEntity = new ResponseEntity<>();
        serverResponseEntity.setData(data);
        serverResponseEntity.setCode(ResponseEntityEnum.OK.value());
        serverResponseEntity.setMessage(ResponseEntityEnum.OK.getMessage());
        return serverResponseEntity;
    }
    /**
     * @author LaoCat
     * @date 2022/6/16
     * @description 成功 无返回体
     * @return org.laocat.core.response.structure.ResponseEntity<T>
     */
    public static <T> ResponseEntity<T> success() {
        ResponseEntity<T> serverResponseEntity = new ResponseEntity<>();
        serverResponseEntity.setCode(ResponseEntityEnum.OK.value());
        serverResponseEntity.setMessage(ResponseEntityEnum.OK.getMessage());
        return serverResponseEntity;
    }

    /**
     * @author LaoCat
     * @date 2022/6/16
     * @description 失败 无返回体
     * @return org.laocat.core.response.structure.ResponseEntity<T>
     */
    public static <T> ResponseEntity<T> fail(ResponseEntityEnum responseEnum) {
        ResponseEntity<T> serverResponseEntity = new ResponseEntity<>();
        serverResponseEntity.setMessage(responseEnum.getMessage());
        serverResponseEntity.setCode(responseEnum.value());
        return serverResponseEntity;
    }
    /**
     * @author LaoCat
     * @date 2022/6/16
     * @description 成功 有返回体
     * @return org.laocat.core.response.structure.ResponseEntity<T>
     */
    public static <T> ResponseEntity<T> fail(ResponseEntityEnum responseEnum, T data) {
        ResponseEntity<T> serverResponseEntity = new ResponseEntity<>();
        serverResponseEntity.setMessage(responseEnum.getMessage());
        serverResponseEntity.setCode(responseEnum.value());
        serverResponseEntity.setData(data);
        return serverResponseEntity;
    }



}
