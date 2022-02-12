package com.khoa.bot.connector.facebook.service;

import com.khoa.bot.connector.facebook.adapter.event.content.message.PostbackContent;
import com.khoa.bot.connector.facebook.adapter.model.Message;

import java.util.List;

public interface PostbackHandler {
    List<Message> getMessage(PostbackContent postbackContent);
}
