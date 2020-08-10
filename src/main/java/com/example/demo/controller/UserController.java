package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.producer.KafkaNewProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;

@Controller
public class UserController {

    private final KafkaNewProducer producer;

    @Autowired
    public UserController(KafkaNewProducer producer) {
        this.producer = producer;
    }
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String TOPIC = "test";
    @PostMapping(value = "/user")
    public String sendMessageToKafkaTopic(@ModelAttribute User user, Model model) {
        model.addAttribute("userR",user);
        this.producer.sendRecord(user);
        return "userR.html";
    }
    @GetMapping("/user")
    public String greetingForm(Model model) {
        model.addAttribute("userS", new User());
        return "userS.html";//this creates the html file
    }

}
