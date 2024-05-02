package com.javahomework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.javahomework.mapper")
public class JavaHomeWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaHomeWorkApplication.class, args);
    }


}
