package com.khoa.bot.connector.facebook.service;

import com.khoa.bot.connector.facebook.adapter.event.IncomingEventEntry;
import com.khoa.bot.connector.facebook.adapter.model.Message;

import java.util.List;

public interface MessageProducer {
    List<Message> getMessage(IncomingEventEntry incomingEventEntry);
}
