package com.khoa.bot.connector.facebook.entity;

public class Message {
    private String eventId;
    private String senderId;
    private String recipientId;
    private String content;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
