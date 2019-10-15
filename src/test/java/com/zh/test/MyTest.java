package com.zh.test;

import com.zh.entity.Alarm;
import com.zh.entity.ListQuery;
import com.zh.service.UserService;
import com.zh.utils.JwtUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTest {
    @Autowired
    private Alarm alarm;
    @Autowired
    private ListQuery query;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Test
    public void Token() {

        //存入JWT的payload中生成token
        Map claims = new HashMap<String,Integer>();
        claims.put("admin_username",111);
        String subject = "admin";
        String token = null;
        try {
            //该token过期时间为12h
            token = JwtUtils.createJWT(claims, subject, 1000*60*60*12 );
        } catch (Exception e) {
            throw new RuntimeException("创建Token失败");
        }

        System.out.println("token:"+token);

    }
@Test
    public void ConvertLongToDateTime(){
    Instant ins = Instant.now(); //默认获取的是UTC时区
    System.out.println(ins);
    System.out.println(ins.toEpochMilli()); //ms

    Instant timestamp = Instant.ofEpochMilli(ins.toEpochMilli());
    ZonedDateTime losAngelesTime = timestamp.atZone(ZoneId.of("Asia/Shanghai"));
    System.out.println(losAngelesTime.toLocalDateTime());


    }

}
