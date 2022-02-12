package com.khoa.bot.connector.facebook.service;

import com.khoa.bot.connector.facebook.adapter.model.Message;
import org.springframework.stereotype.Service;

@Service
public class GreetingMessageProducer implements ContextLessMessageProducer {
    @Override
    public Message getMessage() {
        return Message.builder()
                .text("Xin chào bạn!")
                .build();
    }
}
