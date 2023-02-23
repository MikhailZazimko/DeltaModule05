package com.javarush.spring07.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.Driver;

@Configuration
@ComponentScan("com.javarush.spring07")
@Slf4j
public class AppConfig {

    @Bean
    Driver driver(@Value("${spring.datasource.driver-class-name}") String driverClassName) throws ClassNotFoundException {
        try {
            return (Driver) Class.forName(driverClassName)
                    .getConstructor()
                    .newInstance();
        } catch (InvocationTargetException
                 | NoSuchMethodException
                 | IllegalAccessException
                 | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }



}
