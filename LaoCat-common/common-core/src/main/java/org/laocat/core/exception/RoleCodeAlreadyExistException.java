package org.laocat.core.exception;

/**
 * @description: 角色code已存在
 * @author: LaoCat
 * @date: 2022/6/27
 */
public class RoleCodeAlreadyExistException extends LaoCatException{

    public RoleCodeAlreadyExistException(String message) {
        super(message);
    }
}
