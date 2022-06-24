package org.laocat.auth;

/**
 * Redis存储jwt的key前缀
 */
public enum JwtRedisEnum {

    /**
     * token 的key前缀
     */
    TOKEN_KEY_PREFIX("jwt:"),

    /***
     * authentication的key
     */
    AUTHENTICATION_KEY_PREFIX("authentication:");

    private String value;


    JwtRedisEnum(String value) {
        this.value = value;
    }

    /**
     * 获取key
     */
    public static String getTokenKey(String username, String randomKey) {
        return TOKEN_KEY_PREFIX.value + username + ":" + randomKey;
    }

    /**
     * 获取身份认证key
     */
    public static String getAuthenticationKey(String username, String randomKey) {
        return AUTHENTICATION_KEY_PREFIX.value + username + ":" + randomKey;
    }
}
