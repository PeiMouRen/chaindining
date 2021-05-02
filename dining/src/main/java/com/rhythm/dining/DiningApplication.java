package com.rhythm.dining;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableEurekaClient
@SpringBootApplication
@EnableRedisHttpSession
@EnableFeignClients
@MapperScan("com.rhythm.dining.mapper")
public class DiningApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiningApplication.class, args);
    }

}
