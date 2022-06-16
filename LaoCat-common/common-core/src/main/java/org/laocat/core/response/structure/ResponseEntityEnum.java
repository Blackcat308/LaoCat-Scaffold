package org.laocat.core.response.structure;

/**
 * @author LaoCat
 * @date 2022/6/16
 */
public enum ResponseEntityEnum {

    /**
     * ok
     */
    OK("000000", "ok"),
    /**
     * 未授权
     */
    UNAUTHORIZED("A000401", "Unauthorized"),
    /**
     * 未授权
     */
    TOKEN_EXPIRED("A000401", "token expired"),
    /**
     * 密码错误或验证失败
     */
    PASSWORD_VERIFICATION_ERROR("A000400", "Incorrect password or verification failed");

    private final String code;

    private final String message;

    ResponseEntityEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String value() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ResponseEntityEnum{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
