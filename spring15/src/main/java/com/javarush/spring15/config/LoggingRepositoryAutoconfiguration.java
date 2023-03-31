package com.javarush.spring15.config;

import com.javarush.spring15.logger.LocatorAspect;
import com.javarush.spring15.logger.LogAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties(LoggingRepositoryProperties.class)
@ConditionalOnClass(LoggingRepositoryProperties.class)
public class LoggingRepositoryAutoconfiguration {

    @Bean
    @ConditionalOnMissingBean
    public LocatorAspect locatorAspect() {
        return new LocatorAspect();
    }

    @Bean
    @ConditionalOnMissingBean
    public LogAspect logAspect() {
        return new LogAspect();
    }

    @Bean
    @ConditionalOnMissingBean
    public LoggingRepositoryProperties loggingRepositoryProperties(){
        return new LoggingRepositoryProperties();
    }

}
