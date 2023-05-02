package com.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    @Autowired
    private KafkaTemplate<String, Object> template;

    private String topic = "user-topic";

    @GetMapping("/publish/{name}")
    public String publishMessage(@PathVariable String name) {
        template.send(topic, "Hi " + name + " Welcome to java techie");
        return "Data published";
    }

    @GetMapping("/publishJson")
    public String publishMessage() {
        User user = new User(1001, "Jagadish", new String[] { "Hyderabad", "Bnr", "H.No.118" });
        template.send(topic, user);
        return "Json Data published";
    }
}
