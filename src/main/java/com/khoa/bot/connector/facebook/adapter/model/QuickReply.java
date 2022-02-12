package com.khoa.bot.connector.facebook.adapter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khoa.bot.connector.facebook.adapter.enums.QuickReplyContentType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public abstract class QuickReply {
    @JsonProperty("content_type")
    protected QuickReplyContentType contentType;
    protected String title;
    protected Object payload;
    @JsonProperty("image_url")
    protected String imageUrl;

    public QuickReplyContentType getContentType() {
        return contentType;
    }

    public void setContentType(QuickReplyContentType contentType) {
        this.contentType = contentType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
