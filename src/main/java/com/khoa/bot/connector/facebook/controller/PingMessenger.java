package com.khoa.bot.connector.facebook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping-message")
public class PingMessenger {

    @GetMapping
    public boolean pingUser() {
        return true;
    }
}
