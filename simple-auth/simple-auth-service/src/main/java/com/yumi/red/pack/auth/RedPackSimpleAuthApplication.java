package com.yumi.red.pack.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yumi.red.pack.auth.mapper")
public class RedPackSimpleAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedPackSimpleAuthApplication.class, args);
    }
}