package com.bcaliskan.kafkademo.service;

import com.bcaliskan.kafkademo.model.Greeting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
public class Producer {

    private static final String TOPIC = "kafka-demo";

    @Autowired
    private KafkaTemplate<String, Greeting> kafkaTemplate;


    public void sendMessage(Greeting greeting) {
        // The send API returns a ListenableFuture object.
        ListenableFuture<SendResult<String, Greeting>> future = kafkaTemplate.send(TOPIC, greeting);
        // Kafka is a fast stream processing platform. So it's a better idea to handle the results asynchronously so that
        // the subsequent messages do not wait for the result of the previous message. We can do this through a callback:
        future.addCallback(new ListenableFutureCallback<SendResult<String, Greeting>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("Unable to send message=['{}'] due to : {}", greeting.getMessage(), throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Greeting> result) {
                log.info("Sent message=['{}'] with partition=['{}'] and offset ['{}']", greeting.getMessage(),
                        result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
            }
        });
    }

}