package com.khoa.bot.connector.facebook.context;

import com.khoa.bot.connector.facebook.adapter.event.nlp.NlpData;

public class BotMessageContent {
    private String text;
    private String payload;
    private NlpData nlpData;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public NlpData getNlpData() {
        return nlpData;
    }

    public void setNlpData(NlpData nlpData) {
        this.nlpData = nlpData;
    }

    public static BotMessageContentBuilder builder() {
        return new BotMessageContentBuilder();
    }

    public static final class BotMessageContentBuilder {
        private String text;
        private String payload;
        private NlpData nlpData;

        private BotMessageContentBuilder() {
        }

        public BotMessageContentBuilder text(String text) {
            this.text = text;
            return this;
        }

        public BotMessageContentBuilder payload(String payload) {
            this.payload = payload;
            return this;
        }

        public BotMessageContentBuilder nlpData(NlpData nlpData) {
            this.nlpData = nlpData;
            return this;
        }

        public BotMessageContent build() {
            BotMessageContent botMessageContent = new BotMessageContent();
            botMessageContent.setText(text);
            botMessageContent.setPayload(payload);
            botMessageContent.setNlpData(nlpData);
            return botMessageContent;
        }
    }
}
