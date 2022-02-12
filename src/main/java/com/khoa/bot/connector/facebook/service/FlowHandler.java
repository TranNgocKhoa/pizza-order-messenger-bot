package com.khoa.bot.connector.facebook.service;

import com.khoa.bot.connector.facebook.context.BotMessageContent;
import com.khoa.bot.connector.facebook.entity.User;
import com.khoa.bot.connector.facebook.adapter.model.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FlowHandler {
    private final GreetingMessageProducer greetingMessageProducer;
    private final GeneralContextHandler generalContextHandler;
    private final PizzaOrderContextHandler pizzaOrderContextHandler;

    public FlowHandler(GreetingMessageProducer greetingMessageProducer, GeneralContextHandler generalContextHandler, PizzaOrderContextHandler pizzaOrderContextHandler) {
        this.greetingMessageProducer = greetingMessageProducer;
        this.generalContextHandler = generalContextHandler;
        this.pizzaOrderContextHandler = pizzaOrderContextHandler;
    }

    public List<Message> getMessage(User user, BotMessageContent messageContent) {
        switch (user.getConversationContext()) {
            case GENERAL:
                return generalContextHandler.getMessages(messageContent);
            case ORDER_PIZZA:
                return pizzaOrderContextHandler.getMessages(messageContent);
        }

        return List.of(greetingMessageProducer.getMessage());
    }
}
