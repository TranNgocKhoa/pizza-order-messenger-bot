package com.khoa.bot.connector.facebook.service;

import com.khoa.bot.connector.facebook.adapter.model.Message;
import com.khoa.bot.connector.facebook.context.BotMessageContent;
import com.khoa.bot.connector.facebook.context.DialogContext;
import com.khoa.bot.connector.facebook.entity.User;
import com.khoa.bot.connector.facebook.entity.enums.UserOrderContextType;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PizzaOrderContextHandler extends AbstractUserContextHandler {
    private final ApplicationContext applicationContext;

    public PizzaOrderContextHandler(DialogContext dialogContext, ApplicationContext applicationContext) {
        super(dialogContext);
        this.applicationContext = applicationContext;
    }

    @Override
    public List<Message> getMessages(BotMessageContent messageContent) {
        User contextUser = dialogContext.getUserContext().getContext();
        UserOrderContextType userOrderContextType = contextUser.getOrderContext();

        return applicationContext.getBean(userOrderContextType.getHandler()).getMessages(dialogContext, messageContent);
    }
}
