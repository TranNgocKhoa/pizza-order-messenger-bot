package com.khoa.bot.connector.facebook.adapter.event.content.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductElement {
    private String id;
    @JsonProperty("retailer_id")
    private String retailerId;
    @JsonProperty("image_url")
    private String imageUrl;
    private String title;
    private String subtitle;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(String retailerId) {
        this.retailerId = retailerId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
