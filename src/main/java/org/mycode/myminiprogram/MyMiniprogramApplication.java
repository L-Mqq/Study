package org.mycode.myminiprogram;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.mycode.myminiprogram.mapper")  //指定去哪里寻找mapper 后面在mapper里面就不用单独添加注解了
public class MyMiniprogramApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyMiniprogramApplication.class, args);
    }

}
