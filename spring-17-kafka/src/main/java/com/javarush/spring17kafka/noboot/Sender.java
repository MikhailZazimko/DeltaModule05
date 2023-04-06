package com.javarush.spring17kafka.noboot;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

public class Sender {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        for (int i = 0; i < 20; i++) {
            context.getBean(Sender.class).send("========================  test "+i+"=================================", 42);
        }
    }

    private final KafkaTemplate<Integer, String> template;

    public Sender(KafkaTemplate<Integer, String> template) {
        this.template = template;
    }

    public void send(String toSend, int key) {
        this.template.send("topicNoBoot", key, toSend);
    }

}

