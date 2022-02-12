package com.khoa.bot.connector.facebook.context;

import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

@Service
public class DialogContext {
    private final UserContext userContext;
    private final OrderContext orderContext;

    public DialogContext(UserContext userContext, OrderContext orderContext) {
        this.userContext = userContext;
        this.orderContext = orderContext;
    }

    @Nonnull
    public UserContext getUserContext() {
        return userContext;
    }

    @Nonnull
    public OrderContext getOrderContext() {
        orderContext.initOrder(userContext.getContext());

        return orderContext;
    }
}
