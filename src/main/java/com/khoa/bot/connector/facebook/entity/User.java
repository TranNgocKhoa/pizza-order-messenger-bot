package com.khoa.bot.connector.facebook.entity;

import com.khoa.bot.connector.facebook.entity.enums.ConversationContext;
import com.khoa.bot.connector.facebook.entity.enums.UserOrderContextType;

public class User {
    private long id;
    private String facebookId;
    private String userRef;

    private ConversationContext conversationContext;
    private UserOrderContextType userOrderContextType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getUserRef() {
        return userRef;
    }

    public void setUserRef(String userRef) {
        this.userRef = userRef;
    }

    public ConversationContext getConversationContext() {
        return conversationContext;
    }

    public void setConversationContext(ConversationContext conversationContext) {
        this.conversationContext = conversationContext;
    }

    public UserOrderContextType getOrderContext() {
        return userOrderContextType;
    }

    public void setOrderContext(UserOrderContextType userOrderContextType) {
        this.userOrderContextType = userOrderContextType;
    }

    public CatalogueData getCatalogueData() {
        return new CatalogueData();
    }
}
