package org.laocat.auth;

import java.security.MessageDigest;

import static org.laocat.constant.AuthConstant.SALT;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description
 */
public class MD5Util {


    public static String encode(String password) {
        password = String.join(password, SALT);
        MessageDigest md5;

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        char[] charArray = password.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }

        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();

        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }

            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.encode("laocat"));
    }
}