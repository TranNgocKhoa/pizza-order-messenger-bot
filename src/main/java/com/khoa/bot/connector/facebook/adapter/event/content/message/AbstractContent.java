package com.khoa.bot.connector.facebook.adapter.event.content.message;

public abstract class AbstractContent {
    protected String mid;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }
}
