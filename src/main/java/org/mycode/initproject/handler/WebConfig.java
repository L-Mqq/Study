package org.mycode.initproject.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired JwtHandler jwtHandler;
    //1.收集不需要身份认证的接口
    private final ArrayList<String> excludePath = new ArrayList<>(
            Arrays.asList("/api/user/login/code","/api/user/register","/api/user/sendCaptcha","/api/user/login/password","/api/user/refresh")
    );
    //2.对以上接口进行排除

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtHandler).addPathPatterns("/**").excludePathPatterns(excludePath);
    }


    //3.配置跨域


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("POST","GET","DELETE","PUT","OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
