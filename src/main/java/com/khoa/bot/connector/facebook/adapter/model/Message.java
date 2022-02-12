package com.khoa.bot.connector.facebook.adapter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Message extends AbstractBaseModel {
    private String text;
    private Attachment attachment;
    @JsonProperty("quick_replies")
    private List<QuickReply> quickReplies;
    private String metadata;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public List<QuickReply> getQuickReplies() {
        return quickReplies;
    }

    public void setQuickReplies(List<QuickReply> quickReplies) {
        this.quickReplies = quickReplies;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public static Message fromText(String text) {
        Message message = new Message();
        message.setText(text);

        return message;
    }

    public static Message fromQuickReply(List<QuickReply> quickReplies, String text) {
        Message message = new Message();
        message.setQuickReplies(quickReplies);
        message.setText(text);

        return message;
    }

    public static MessageBuilder builder() {
        return new MessageBuilder();
    }

    public static final class MessageBuilder {
        private String text;
        private Attachment attachment;
        private List<QuickReply> quickReplies;
        private String metadata;

        private MessageBuilder() {
        }

        public MessageBuilder text(String text) {
            this.text = text;
            return this;
        }

        public MessageBuilder attachment(Attachment attachment) {
            this.attachment = attachment;
            return this;
        }

        public MessageBuilder quickReplies(List<QuickReply> quickReplies) {
            this.quickReplies = quickReplies;
            return this;
        }

        public MessageBuilder metadata(String metadata) {
            this.metadata = metadata;
            return this;
        }

        public Message build() {
            Message message = new Message();
            message.setText(text);
            message.setAttachment(attachment);
            message.setQuickReplies(quickReplies);
            message.setMetadata(metadata);
            return message;
        }
    }
}
