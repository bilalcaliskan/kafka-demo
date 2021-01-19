package com.bcaliskan.kafkademo.service;

import com.bcaliskan.kafkademo.model.Greeting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Consumer {

    private static final String TOPIC_NAME = "kafka-demo";
    private static final String GROUP_NAME = "kafka-demo";

    @KafkaListener(topics = TOPIC_NAME, groupId = GROUP_NAME)
    public void listenWithHeaders(@Payload Greeting greeting, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                                  @Header(KafkaHeaders.OFFSET) long offset) {
        log.info("Received message=['{}'] with partition=['{}'] and offset ['{}']", greeting.getMessage(), partition, offset);
    }

}