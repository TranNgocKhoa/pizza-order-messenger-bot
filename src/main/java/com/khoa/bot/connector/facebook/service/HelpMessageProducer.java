package com.khoa.bot.connector.facebook.service;

import com.khoa.bot.connector.facebook.adapter.event.content.message.MessageContent;
import com.khoa.bot.connector.facebook.adapter.model.Message;
import com.khoa.bot.connector.facebook.context.BotMessageContent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HelpMessageProducer implements ContextLessMessageProducer {

    private final String version;

    public HelpMessageProducer(@Value("${version}") String version) {
        this.version = version;
    }

    @Override
    public Message getMessage() {
        return Message.builder()
                .text(String.format("Tin nhắn của bạn đang được phản hồi bởi Ứng dụng Stupid Bot. Phiên bản %s", version))
                .build();
    }

    public boolean isHelp(BotMessageContent messageContent) {
        return StringUtils.containsAnyIgnoreCase(messageContent.getText(), "help", "cứu giúp", "hỗ trợ");
    }
}
