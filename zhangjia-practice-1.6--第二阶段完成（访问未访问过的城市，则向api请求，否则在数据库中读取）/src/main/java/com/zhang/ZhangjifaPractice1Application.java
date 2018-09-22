package com.zhang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

//@EnableJpaRepositories("com...jpa")
//@EntityScan("com...jpa")
//@EnableJpaRepositories("com.zhang.repository")
//@ComponentScan("com.zhang.*")
//@MapperScan("com.zhang.*")
@SpringBootApplication
public class ZhangjifaPractice1Application  {

    public static void main(String[] args) {
        SpringApplication.run(ZhangjifaPractice1Application.class, args);
    }
}
