package com.zh.utils;/**
 * ClassName: ConvertLongToDateTime <br/>
 * Description: <br/>
 * date: 2019/10/12 下午3:04<br/>
 *
 * @author Hesion<br />
 * @version
 * @since JDK 1.8
 */

import org.springframework.util.Assert;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @program: zhbackend
 *
 * @description:
 *
 * @author: zxb
 *
 * @create: 2019-10-12 15:04
 **/
public class ConvertLongToDateTime {

    /**
     * 将Long类型的时间戳转换成String 类型的时间格式，时间格式为：yyyy-MM-dd HH:mm:ss
     */
    public static String convertTimeToString(Long time){
        Assert.notNull(time, "time is null");
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
    }
}
