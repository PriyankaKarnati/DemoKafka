package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final Producer producer;

    @Autowired
    public UserController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping("/user")
    public String sendMessageToKafkaTopic(@ModelAttribute User user, Model model) {
        model.addAttribute("userR",user);
        this.producer.sendRecord(user);
        return "done";
    }
    @GetMapping("/user")
    public String greetingForm(Model model) {
        model.addAttribute("userS", new User());
        return "hello";
    }
}
