package com.khoa.bot.connector.facebook.entity;

import com.khoa.bot.connector.facebook.entity.enums.UserOrderContextType;

public class UserOrderContext {
    private long userId;
    private String facebookId;
    private UserOrderContextType userOrderContextType;

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

    public UserOrderContextType getOrderContext() {
        return userOrderContextType;
    }

    public void setOrderContext(UserOrderContextType userOrderContextType) {
        this.userOrderContextType = userOrderContextType;
    }
}
