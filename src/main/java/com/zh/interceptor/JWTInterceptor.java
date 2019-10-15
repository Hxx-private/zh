package com.zh.interceptor;

import com.zh.service.UserService;
import com.zh.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @author Hxx
 * JWT token 管理后台的拦截校验器
 */
@Component
public class JWTInterceptor implements HandlerInterceptor {
@Autowired
private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 允许跨域
        //response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许自定义请求头token(允许head跨域)
         response.setHeader("Access-Control-Allow-Headers", "token, Accept, Origin, authorization,content-type,X-Requested-With, Content-Type, Last-Modified");

        //后台管理页面产生的token
        String token = request.getHeader("authorization");
        //判断是否过期
        Optional.ofNullable(token)
                .map(n -> {
                    try {
                        return JwtUtils.parseJWT(n);
                    } catch (Exception e) {
                        throw new RuntimeException("token不存在");
                    }
                });

        return true;
    }

}

