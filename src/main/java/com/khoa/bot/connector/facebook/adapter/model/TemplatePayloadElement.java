package com.khoa.bot.connector.facebook.adapter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TemplatePayloadElement {
    private String title;
    @JsonProperty("image_url")
    private String imageUrl;
    private String subtitle;
    @JsonProperty("default_action")
    private Button defaultAction;
    private List<Button> buttons;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Button getDefaultAction() {
        return defaultAction;
    }

    public void setDefaultAction(Button defaultAction) {
        this.defaultAction = defaultAction;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }
}
