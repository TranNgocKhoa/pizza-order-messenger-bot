package com.khoa.bot.connector.facebook.adapter.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendApiResponse {
    @JsonProperty("recipient_id")
    private String recipientId;
    @JsonProperty("message_id")
    private String messageId;

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
