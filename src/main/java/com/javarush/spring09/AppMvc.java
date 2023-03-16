package com.javarush.spring09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:cfg/application-09.properties")
@SpringBootApplication
public class AppMvc {
    public static void main(String[] args) {
        SpringApplication.run(AppMvc.class, args);
    }
}
