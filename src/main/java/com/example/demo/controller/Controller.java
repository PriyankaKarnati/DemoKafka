package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    private final Producer producer;

    @Autowired
    public Controller(Producer producer) {
        this.producer = producer;
    }

    @PostMapping("/detail")
    public String sendMessageToKafkaTopic(@ModelAttribute User user) {
        this.producer.sendRecord(user);
        return "record sent to topic";
    }
    @GetMapping("/detail")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new User());
        return "record created";
    }
}
