package com.heima.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.heima.user.mapper")
public class UserApplication {
    public static void main(String[] args) {
        System.out.println("周小黑");
        SpringApplication.run(UserApplication.class, args);
    }
}
