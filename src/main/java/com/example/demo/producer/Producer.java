package com.example.demo.producer;

import com.example.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "test";

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    public void sendRecord(User user) {
        logger.info(String.format("#### -> Producing message -> %s", user.toString()));
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setAge(user.getAge());
        this.kafkaTemplate.send(TOPIC,newUser);
    }

}
