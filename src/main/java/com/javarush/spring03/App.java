package com.javarush.spring03;

import com.javarush.spring03.config.AppConfig;
import com.javarush.spring03.entity.Customer;
import com.javarush.spring03.service.CustomerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {


    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        try (context){
            long id = 1L;
            CustomerService customerService = context.getBean(CustomerService.class);
            Customer customer = customerService.get(id);
            System.out.println(customerService.get(id));
            customer.setPassword("test12345");
            customerService.update(customer);
            System.out.println(customerService.get(id));
            customer.setPassword("456");
            customerService.update(customer);
            System.out.println(customerService.get(id));
        }
    }
}
