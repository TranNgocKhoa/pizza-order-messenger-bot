package com.khoa.bot.connector.facebook.service;

import com.khoa.bot.connector.facebook.adapter.model.Message;

public interface ContextLessMessageProducer {
    Message getMessage();
}
