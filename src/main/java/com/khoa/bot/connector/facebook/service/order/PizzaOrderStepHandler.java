package com.khoa.bot.connector.facebook.service.order;

import com.khoa.bot.connector.facebook.adapter.model.Message;
import com.khoa.bot.connector.facebook.context.BotMessageContent;
import com.khoa.bot.connector.facebook.context.DialogContext;
import com.khoa.bot.connector.facebook.entity.User;
import com.khoa.bot.connector.facebook.entity.enums.UserOrderContextType;

import java.util.List;
import java.util.Optional;

public interface PizzaOrderStepHandler {

    default void setNextStep(DialogContext dialogContext, UserOrderContextType nextStep) {
        User context = dialogContext.getUserContext().getContext();
        dialogContext.getUserContext().updateOrderContext(context, nextStep);
    }

    default Optional<String> getPayload(BotMessageContent messageContent) {
        return Optional.of(messageContent)
                .map(BotMessageContent::getPayload)
                .filter(payload -> !payload.isEmpty());
    }

    List<Message> getMessages(DialogContext dialogContext, BotMessageContent botMessageContent);
}
