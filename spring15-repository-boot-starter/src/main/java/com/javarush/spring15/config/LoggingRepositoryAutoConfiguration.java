package com.javarush.spring15.config;

import com.javarush.spring15.logger.LocatorAspect;
import com.javarush.spring15.logger.LogAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnClass(LoggingRepositoryProperties.class)
@ConditionalOnProperty(
        prefix = LoggingRepositoryProperties.PREFIX,
        name = "state",
        havingValue = "ON"
)
@EnableConfigurationProperties(LoggingRepositoryProperties.class)
public class LoggingRepositoryAutoConfiguration {

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


}
