package com.zh.config;

import com.zh.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    /**
     * spring拦截器加载在springcontentText之前，所以这里用@Bean提前加载。
     * 否则会导致过滤器中的@AutoWired注入为空
     */
    @Bean
    JWTInterceptor jwtInterceptor() {
        return new JWTInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        System.out.println("JWT拦截器启动");
        registry.addInterceptor(jwtInterceptor())
                .excludePathPatterns("/user/login",
                        "/api/login",
                        "/admin/logout",
                        "/error",
                        "/admin/getOnlineCount")
                .addPathPatterns();

    }

    /**
     * 解决跨域问题
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }


}

