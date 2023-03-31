package com.javarush.spring13and14;

import com.javarush.spring13and14.model.dto.UserDto;
import com.javarush.spring13and14.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppTestLesson13and14 {
    public static void main(String[] args) {
        var context = SpringApplication.run(AppTestLesson13and14.class, args);
        UserService userService = context.getBean(UserService.class);
        UserDto user = userService.get(1L).block();
        System.out.println(user);
    }
}
