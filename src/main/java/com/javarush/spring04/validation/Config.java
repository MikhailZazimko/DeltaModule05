package com.javarush.spring04.validation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PreDestroy;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Configuration
@ComponentScan
@PropertySource("classpath:application-04.properties")
public class Config {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    @Bean(value = "demoQualifierValidator")
    Validator validator() {
        return validatorFactory.getValidator();
    }

    @PreDestroy
    void close() {
        validatorFactory.close();
    }
}
