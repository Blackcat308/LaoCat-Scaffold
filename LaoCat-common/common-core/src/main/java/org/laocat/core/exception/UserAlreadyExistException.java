package org.laocat.core.exception;

/**
 * @description: 用户已存在
 * @author: LaoCat
 * @date: 2022/6/27
 */
public class UserAlreadyExistException extends LaoCatException{

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
