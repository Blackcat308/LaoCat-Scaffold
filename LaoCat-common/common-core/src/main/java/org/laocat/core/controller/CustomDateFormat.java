package org.laocat.core.controller;

import cn.hutool.core.date.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: 自定义时间格式化
 * @author: LaoCat
 * @date: 2022/6/27
 */
public class CustomDateFormat extends SimpleDateFormat {

    /**
     * @description: 解析格式如下
     * yyyy-MM-dd HH:mm:ss
     * yyyy/MM/dd HH:mm:ss
     * yyyy.MM.dd HH:mm:ss
     * yyyy年MM月dd日 HH时mm分ss秒
     * yyyy-MM-dd
     * yyyy/MM/dd
     * yyyy.MM.dd
     * HH:mm:ss
     * HH时mm分ss秒
     * yyyy-MM-dd HH:mm
     * yyyy-MM-dd HH:mm:ss.SSS
     * yyyyMMddHHmmss
     * yyyyMMddHHmmssSSS
     * yyyyMMdd
     * EEE, dd MMM yyyy HH:mm:ss z
     * EEE MMM dd HH:mm:ss zzz yyyy
     * yyyy-MM-dd'T'HH:mm:ss'Z'
     * yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
     * yyyy-MM-dd'T'HH:mm:ssZ
     * yyyy-MM-dd'T'HH:mm:ss.SSSZ
     * @author: LaoCat
     * @date: 2022/6/27
     * @returnType: java.util.Date
     */
    @Override
    public Date parse(String source) throws ParseException {
        return DateUtil.parse(source);
    }
}
