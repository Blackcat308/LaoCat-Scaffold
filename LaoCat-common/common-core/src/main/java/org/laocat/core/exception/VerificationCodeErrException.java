package org.laocat.core.exception;

/**
 * @description: 密码不正确
 * @author: LaoCat
 * @date: 2022/6/27
 */
public class VerificationCodeErrException extends LaoCatException {

    public VerificationCodeErrException(String message) {
        super(message);
    }
}
