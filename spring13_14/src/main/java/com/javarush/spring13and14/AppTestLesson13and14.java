package com.javarush.spring13and14;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class AppTestLesson13and14 {
    public static void main(String[] args) {
        SpringApplication.run(AppTestLesson13and14.class, args);
    }
}
