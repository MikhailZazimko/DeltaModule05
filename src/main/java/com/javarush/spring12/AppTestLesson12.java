package com.javarush.spring12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:cfg/application-10.properties")
public class AppTestLesson12 {
    public static void main(String[] args) {
        SpringApplication.run(AppTestLesson12.class, args);
    }
}
