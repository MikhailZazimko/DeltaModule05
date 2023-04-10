package com.javarush.spring17kafka.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class AppProducer {

    public static void main(String[] args) {
        SpringApplication.run(AppProducer.class, args);
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("topic1")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public ApplicationRunner runner(KafkaTemplate<String, String> template) {
        return args -> {
            Runnable runnable=()->{
                template.send("topic1", "============ AppProducer started ==============");

                for (int i = 0; i < 100; i++) {
                    template.send("topic1", "test "+i);
                    sleep(ThreadLocalRandom.current().nextInt(200));
                }
                template.send("topic1", "============== AppProducer finished =================");
            };
            new Thread(runnable).start();
        };
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

