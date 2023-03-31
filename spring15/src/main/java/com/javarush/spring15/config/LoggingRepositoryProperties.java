package com.javarush.spring15.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Slf4j
@NoArgsConstructor
@ConfigurationProperties(prefix = "logging.repository")
public class LoggingRepositoryProperties {
    /**
     * set log level (INFO WARNING etc)
     */
    private String level;

    /**
     * set "on" for start logging
     **/
    private String state;


}
