package com.example.demo.producer;

import com.example.demo.model.User;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
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
//    private Properties prop ;
//    public KafkaNewProducer(){
//        Properties props = new Properties();
//        props.put("key.serializer",
//                "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer",
//                "com.example.demo.model.UserSerializer");
//        return props;
//    }

//    public void setProp(Properties pro){
//        this.prop = pro;
//    }

    public void sendRecord(User user) {

//        User newUser = new User();
        Properties props = new Properties();
        props.put("bootstrap.servers","localhost:9092");
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                "com.example.demo.model.UserSerializer");

//        newUser.setName(user.getName());
//        newUser.setAge(user.getAge());
        try(Producer<String,User> kafkaProd= new KafkaProducer<String,User>(props)) {
            kafkaProd.send(new ProducerRecord<>(TOPIC,new User(user.getName(), user.getAge())));//check for efficiency
            logger.info(String.format("#### -> Producing message -> %s", user.toString()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
