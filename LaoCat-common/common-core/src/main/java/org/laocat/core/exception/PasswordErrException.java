package org.laocat.core.exception;

/**
 * @description: 密码不正确
 * @author: LaoCat
 * @date: 2022/6/27
 */
public class PasswordErrException extends LaoCatException{

    public PasswordErrException(String message) {
        super(message);
    }
}
