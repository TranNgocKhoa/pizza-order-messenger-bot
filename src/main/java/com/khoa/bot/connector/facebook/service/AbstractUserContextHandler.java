package com.khoa.bot.connector.facebook.service;

import com.khoa.bot.connector.facebook.context.DialogContext;

public abstract class AbstractUserContextHandler implements ContextHandler {
    protected final DialogContext dialogContext;

    protected AbstractUserContextHandler(DialogContext dialogContext) {
        this.dialogContext = dialogContext;
    }

}
