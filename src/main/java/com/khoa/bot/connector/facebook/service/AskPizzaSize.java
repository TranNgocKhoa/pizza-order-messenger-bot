package com.khoa.bot.connector.facebook.service;

import com.khoa.bot.connector.facebook.adapter.enums.QuickReplyContentType;
import com.khoa.bot.connector.facebook.adapter.model.Message;
import com.khoa.bot.connector.facebook.adapter.model.QuickReply;
import com.khoa.bot.connector.facebook.adapter.model.TextQuickReply;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AskPizzaSize {

    public List<Message> getMessage() {
        return List.of(Message.builder()
                .text("Vui lòng chọn loại Pizza")
                .quickReplies(askPizzaSize())
                .build());
    }
    private List<QuickReply> askPizzaSize() {

        return List.of(
                TextQuickReply.builder()
                        .contentType(QuickReplyContentType.TEXT)
                        .payload("pizza_size_big")
                        .title("Big \uD83C\uDF55")
                        .build(),
                TextQuickReply.builder()
                        .contentType(QuickReplyContentType.TEXT)
                        .payload("pizza_size_medium")
                        .title("Medium \uD83C\uDF55")
                        .build(),
                TextQuickReply.builder()
                        .contentType(QuickReplyContentType.TEXT)
                        .payload("pizza_size_small")
                        .title("Small \uD83C\uDF55")
                        .build()
        );
    }
}
