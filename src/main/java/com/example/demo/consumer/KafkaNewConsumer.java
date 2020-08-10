package com.example.demo.consumer;

import com.example.demo.model.CustomerRepository;
import com.example.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaNewConsumer {
    private final Logger logger = LoggerFactory.getLogger(KafkaNewConsumer.class);
    @Autowired
    CustomerRepository repository;

    @KafkaListener(topics = "test", groupId = "groupId",containerFactory = "kafkaListener")
    public void consume(User user){
        repository.save(new User(user.getName(), user.getAge()));
        System.out.println(repository.findAll());
       logger.info(String.format("#### -> Consumed message -> %s",user.toString()));
    }
}
