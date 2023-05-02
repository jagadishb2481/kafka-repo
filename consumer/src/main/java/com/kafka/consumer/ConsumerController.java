package com.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

public class ConsumerController {
    List<String> messages = new ArrayList<>();

    User userFromTopic = null;

    @GetMapping("/consumeStringMessage")
    public List<String> consumeMsg() {
        return messages;
    }

    @GetMapping("/consumeJsonMessage")
    public User consumeJsonMessage() {
        return userFromTopic;
    }

    @KafkaListener(groupId = "jagadish-0", topics = "jagadish-topic0", containerFactory = "kafkaListenerContainerFactory")
    public List<String> getMsgFromTopic(String data) {
        messages.add(data);
        return messages;
    }

    @KafkaListener(groupId = "usergrp", topics = "user-topic", containerFactory = "userKafkaListenerContainerFactory")
    public User getJsonMsgFromTopic(User user) {
        userFromTopic = user;
        return userFromTopic;
    }

}
