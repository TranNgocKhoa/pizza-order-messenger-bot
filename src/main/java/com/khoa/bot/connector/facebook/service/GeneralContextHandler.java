package com.khoa.bot.connector.facebook.service;

import com.khoa.bot.connector.facebook.adapter.model.Message;
import com.khoa.bot.connector.facebook.context.BotMessageContent;
import com.khoa.bot.connector.facebook.context.DialogContext;
import com.khoa.bot.connector.facebook.entity.enums.ConversationContext;
import com.khoa.bot.connector.facebook.entity.enums.FunctionType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class GeneralContextHandler extends AbstractUserContextHandler {
    private final GreetingMessageProducer greetingMessageProducer;
    private final FunctionMessageProducer functionMessageProducer;
    private final HelpMessageProducer helpMessageProducer;
    private final PizzaOrderContextHandler pizzaOrderContextHandler;
    private final LookupMessageProducer lookupMessageProducer;

    public GeneralContextHandler(DialogContext dialogContext,
                                 GreetingMessageProducer greetingMessageProducer,
                                 FunctionMessageProducer functionMessageProducer,
                                 HelpMessageProducer helpMessageProducer,
                                 PizzaOrderContextHandler pizzaOrderContextHandler, LookupMessageProducer lookupMessageProducer) {
        super(dialogContext);
        this.greetingMessageProducer = greetingMessageProducer;
        this.functionMessageProducer = functionMessageProducer;
        this.helpMessageProducer = helpMessageProducer;
        this.pizzaOrderContextHandler = pizzaOrderContextHandler;
        this.lookupMessageProducer = lookupMessageProducer;
    }

    @Override
    public List<Message> getMessages(BotMessageContent messageContent) {
        if (Objects.nonNull(messageContent.getNlpData()) && messageContent.getNlpData().ifGreeting()) {
            return sendDefaultWith(greetingMessageProducer.getMessage());
        }

        if (helpMessageProducer.isHelp(messageContent)) {
            return sendDefaultWith(helpMessageProducer.getMessage());
        }

        if (Objects.nonNull(messageContent.getPayload())) {
            switch (FunctionType.fromPayload(messageContent.getPayload())) {
                case ORDER:
                    dialogContext.getUserContext().updateConversationContext(dialogContext.getUserContext().getContext(), ConversationContext.ORDER_PIZZA);

                    return pizzaOrderContextHandler.getMessages(messageContent);
                case HELP:
                    return sendDefaultWith(helpMessageProducer.getMessage());
                case LOOKUP:
                    return sendDefaultWith(lookupMessageProducer.getMessages(messageContent).get(0));
                default:
            }
        }

        return sendDefaultWith(greetingMessageProducer.getMessage());
    }

    private List<Message> sendDefaultWith(Message message) {
        return Arrays.asList(
                message,
                functionMessageProducer.getMessage()
        );
    }
}
