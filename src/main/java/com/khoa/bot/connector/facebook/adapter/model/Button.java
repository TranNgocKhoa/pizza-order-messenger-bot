package com.khoa.bot.connector.facebook.adapter.model;

import com.khoa.bot.connector.facebook.adapter.enums.ActionType;

public class Button extends AbstractBaseModel {
    private ActionType type;
    private String url;
    private String title;
    private String payload;

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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

    public static WebUrlButtonBuilder webUrlButtonBuilder() {
        return new WebUrlButtonBuilder();
    }

    public static PostbackButtonBuilder postbackButtonBuilder() {
        return new PostbackButtonBuilder();
    }

    public static final class WebUrlButtonBuilder {
        private String url;
        private String title;

        private WebUrlButtonBuilder() {
        }

        public WebUrlButtonBuilder url(String url) {
            this.url = url;
            return this;
        }

        public WebUrlButtonBuilder title(String title) {
            this.title = title;
            return this;
        }

        public Button build() {
            Button button = new Button();
            button.setType(ActionType.WEB_URL);
            button.setUrl(url);
            button.setTitle(title);
            return button;
        }
    }

    public static final class PostbackButtonBuilder {
        private String title;
        private String payload;

        private PostbackButtonBuilder() {
        }

        public PostbackButtonBuilder title(String title) {
            this.title = title;
            return this;
        }

        public PostbackButtonBuilder payload(String payload) {
            this.payload = payload;
            return this;
        }

        public Button build() {
            Button button = new Button();
            button.setType(ActionType.POSTBACK);
            button.setTitle(title);
            button.setPayload(payload);
            return button;
        }
    }

}
