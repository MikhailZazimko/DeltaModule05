package com.javarush.spring17kafka.noboot;

import org.springframework.kafka.annotation.KafkaListener;

class Listener {

    @KafkaListener(id = "listen1", topics = "topicNoBoot")
    public void listen1(String in) {
        System.out.println(in);
    }

}
