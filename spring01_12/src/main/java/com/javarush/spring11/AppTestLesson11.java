package com.javarush.spring11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@PropertySource("classpath:cfg/application-10.properties")
public class AppTestLesson11 {
    public static void main(String[] args) {
        SpringApplication.run(AppTestLesson11.class, args);
    }
}
