package com.zh.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hxx
 */
public class TokenUtils {
    public static String getToken(String userId) {

        //存入JWT的payload中生成token
        Map claims = new HashMap<String,Integer>();
        claims.put("user_userId",userId);
        String subject = "user";
        String token = null;
        try {
            //该token过期时间为12h
            token = JwtUtils.createJWT(claims, subject, 1000*60*60*12 );
        } catch (Exception e) {
            throw new RuntimeException("创建Token失败");
        }

        System.out.println("token:"+token);
        return token;
    }
}
