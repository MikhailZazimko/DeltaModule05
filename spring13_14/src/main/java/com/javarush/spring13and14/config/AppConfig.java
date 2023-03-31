package com.javarush.spring13and14.config;

import com.javarush.spring13and14.model.mapper.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    UserMapper userMapper() {
        return Mappers.getMapper(UserMapper.class);
    }
}
