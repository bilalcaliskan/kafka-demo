package com.bcaliskan.kafkademo.controller;

import com.bcaliskan.kafkademo.model.Greeting;
import com.bcaliskan.kafkademo.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    @Autowired
    private Producer producer;

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message, @RequestParam("name") String name) {
        Greeting greeting = Greeting.builder()
                .name(name)
                .message(message)
                .build();
        producer.sendMessage(greeting);
    }

}