package com.khoa.bot.connector.facebook.service;

import com.khoa.bot.connector.facebook.context.BotMessageContent;
import com.khoa.bot.connector.facebook.adapter.model.Message;

import java.util.List;

public interface ContextHandler {
    List<Message> getMessages(BotMessageContent messageContent);
}
