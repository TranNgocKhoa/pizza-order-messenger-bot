package com.khoa.bot.connector.facebook.entity;

public class WebHookRequest {
    private String header;
    private String event;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }


    public static WebHookRequestBuilder builder() {
        return new WebHookRequestBuilder();
    }

    public static final class WebHookRequestBuilder {
        private String header;
        private String event;

        private WebHookRequestBuilder() {
        }

        public WebHookRequestBuilder header(String header) {
            this.header = header;
            return this;
        }

        public WebHookRequestBuilder event(String event) {
            this.event = event;
            return this;
        }

        public WebHookRequest build() {
            WebHookRequest webHookRequest = new WebHookRequest();
            webHookRequest.setHeader(header);
            webHookRequest.setEvent(event);
            return webHookRequest;
        }
    }
}
