package com.javarush.spring07;

import com.javarush.spring07.entity.User;
import com.javarush.spring07.service.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        var context = SpringApplication.run(App.class, args);
        try (context) {
            long id = 1L;
            CustomerService customerService = context.getBean(CustomerService.class);
            User user = customerService.get(id).orElseThrow();
            System.out.println(customerService.get(id));
            user.setPassword("test12345");
            customerService.update(user);
            System.out.println(customerService.get(id));
            user.setPassword("456");
            customerService.update(user);
            System.out.println(customerService.get(id));
        }
    }
}