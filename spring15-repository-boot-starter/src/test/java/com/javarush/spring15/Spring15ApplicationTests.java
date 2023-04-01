package com.javarush.spring15;

import com.javarush.spring15.config.LoggingRepositoryAutoConfiguration;
import com.javarush.spring15.config.LoggingRepositoryProperties;
import com.javarush.spring15.logger.LocatorAspect;
import com.javarush.spring15.logger.LogAspect;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;

@Profile("test")
@SpringBootTest
public class Spring15ApplicationTests {

    @Autowired
    ApplicationContext context;

    @Autowired
    LoggingRepositoryProperties properties;

    @Test
    void checkReadProperties(){
        Assertions.assertEquals("ERROR",properties.getLevel());
        Assertions.assertEquals("ON",properties.getState());
    }


    @Test
    void contextLoaded() {
        Class<?>[] beans = {
                LoggingRepositoryAutoConfiguration.class,
                LoggingRepositoryProperties.class,
                LogAspect.class,
                LocatorAspect.class,
        };
        for (Class<?> bean : beans) {
            String name = context.getBean(bean).getClass().getName();
            String expected = bean.getName();
            Assertions.assertTrue(
                    name.contains(expected)
            );
        }
    }

}
