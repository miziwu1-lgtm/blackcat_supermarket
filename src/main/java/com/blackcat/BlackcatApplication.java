package com.blackcat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.blackcat.dao")
@SpringBootApplication
public class BlackcatApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.blackcat.BlackcatApplication.class, args);
    }

}
