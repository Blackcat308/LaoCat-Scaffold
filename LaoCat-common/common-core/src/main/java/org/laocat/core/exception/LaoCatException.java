package org.laocat.core.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: @see
 * @author: LaoCat
 * @date: 2022/6/27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class LaoCatException extends RuntimeException {

    private String errMsg;

    public LaoCatException(String message) {
        super(message);
    }

}
