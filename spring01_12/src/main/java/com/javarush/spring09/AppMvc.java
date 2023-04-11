package com.javarush.spring09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@PropertySource("classpath:cfg/application-09.properties")
public class AppMvc {
    public static void main(String[] args) {
        SpringApplication.run(AppMvc.class, args);
    }
}
