package com.khoa.bot.connector.facebook.adapter.event.content.message;

public class PostbackContent extends AbstractContent {
    private String title;
    private String payload;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
