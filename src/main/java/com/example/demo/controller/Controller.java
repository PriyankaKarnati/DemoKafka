package com.example.demo.controller;

import com.example.demo.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class Controller {

    private final Producer producer;

    @Autowired
    public Controller(Producer producer) {
        this.producer = producer;
    }

    @GetMapping(value = "/publish")
    public String sendMessageToKafkaTopic(@RequestParam("message") String message) {
        this.producer.sendMessage(message);
        return "Message sent to topic";
    }
}
