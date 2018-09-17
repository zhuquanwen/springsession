package com.zqw.springsession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@SpringBootApplication
@RestController
public class SpringsessionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsessionApplication.class, args);
    }

    @GetMapping("/test")
    public String test(HttpSession session){
        return session.getId();
    }

}
