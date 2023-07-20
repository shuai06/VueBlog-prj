package com.example.vueblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan({"com.example.vueblog.mapper"})
public class VueBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(VueBlogApplication.class, args);
    }

}
