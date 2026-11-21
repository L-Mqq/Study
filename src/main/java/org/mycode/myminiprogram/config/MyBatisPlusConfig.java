package org.mycode.myminiprogram.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisPlusConfig {

    @Bean  //告诉 Spring：这个方法返回的对象需要交给 Spring 容器管理
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
       //拦截管理器 管理所有插件
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        //创建分页插件
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        //添加分页插件
        mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);
        //返回拦截器
        return mybatisPlusInterceptor;

    }

}
