package org.mycode.myminiprogram.config;

import org.mycode.myminiprogram.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.mycode.myminiprogram.interceptor.LoginInterceptor;

@Configuration
public class Webconfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")          // 拦截所有请求
                .excludePathPatterns(            // 排除不需要登录的路径
                        "/api/user/login",
                        "api//user/register",
                        "/doc.html",             // Swagger文档
                        "/swagger-resources/**",
                        "/webjars/**"
                );
    }
}
