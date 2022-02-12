package com.khoa.bot.connector.facebook.entity;

import com.khoa.bot.connector.facebook.entity.enums.ConversationContext;

public class UserConversationContext {
    private long userId;
    private String facebookId;
    private ConversationContext conversationContext;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public ConversationContext getConversationContext() {
        return conversationContext;
    }

    public void setConversationContext(ConversationContext conversationContext) {
        this.conversationContext = conversationContext;
    }
}
