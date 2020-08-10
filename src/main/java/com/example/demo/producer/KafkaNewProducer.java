package com.example.demo.producer;


import com.example.demo.model.User;
import com.example.demo.model.UserDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class KafkaNewProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaNewProducer.class);
    private static final String TOPIC = "test";

    @Autowired
    private KafkaTemplate<String,User>kafkaTemp;

    public void sendRecord(User user) {
        this.kafkaTemp.send(TOPIC,new User(user.getName(), user.getAge()));
        logger.info(String.format("#### -> Producing message -> %s", user.toString()));
   }

}
