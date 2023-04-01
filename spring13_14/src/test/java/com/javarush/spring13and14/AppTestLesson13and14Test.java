package com.javarush.spring13and14;

import com.javarush.spring15.config.LoggingRepositoryProperties;
import com.javarush.spring15.logger.LocatorAspect;
import com.javarush.spring15.logger.LogAspect;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class AppTestLesson13and14Test {

    @Autowired
    ApplicationContext context;

    @Test
    void contextLoaded() {

        Assertions.assertEquals(
                context.getBean(LoggingRepositoryProperties.class).getClass().getName(),
                "com.javarush.spring15.config.LoggingRepositoryProperties"
        );

        Assertions.assertEquals(
                context.getBean(LogAspect.class).getClass().getName(),
                "com.javarush.spring15.logger.LogAspect"
        );

        Assertions.assertEquals(
                context.getBean(LocatorAspect.class).getClass().getName(),
                "com.javarush.spring15.logger.LocatorAspect"
        );
    }
}