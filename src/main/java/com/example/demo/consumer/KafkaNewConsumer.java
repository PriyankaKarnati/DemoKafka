package com.example.demo.consumer;

import com.example.demo.model.User;
import com.example.demo.producer.KafkaNewProducer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

@Service
public class KafkaNewConsumer {
    private final Logger logger = LoggerFactory.getLogger(KafkaNewProducer.class);

    @KafkaListener(topics = "test", groupId = "group_id")
    public void consume(User user){
        Properties props = new Properties();
        props.put("bootstrap.servers","localhost:9092");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "com.example.demo.model.UserDeserializer");
        try (KafkaConsumer<String, User> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Collections.singletonList("test"));
            while (true) {
                ConsumerRecords<String, User> messages = consumer.poll(100);
                for (ConsumerRecord<String, User> message : messages) {
                    System.out.println("Message received " + message.value().toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //logger.info(String.format("#### -> Consumed message -> %s", user.toString()));

    }
}
