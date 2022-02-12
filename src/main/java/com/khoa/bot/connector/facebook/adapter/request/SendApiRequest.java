package com.khoa.bot.connector.facebook.adapter.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khoa.bot.connector.facebook.adapter.enums.MessageTag;
import com.khoa.bot.connector.facebook.adapter.enums.MessagingType;
import com.khoa.bot.connector.facebook.adapter.enums.NotificationType;
import com.khoa.bot.connector.facebook.adapter.model.Message;
import com.khoa.bot.connector.facebook.adapter.model.Recipient;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Send API is the main API used to send messages to users,
 * including text, attachments, structured message templates, sender actions, and more.
 *
 * A page access token with <i>pages_messaging</i> permission is required to interact with this endpoint.
 */
public class SendApiRequest {
    @JsonProperty("messaging_type")
    private MessagingType messagingType;
    @JsonProperty("recipient")
    private Recipient recipient;
    @JsonProperty("message")
    private Message message;
    @JsonProperty("sender_action")
    private String senderAction;
    @JsonProperty("notification_type")
    private NotificationType notificationType;
    @JsonProperty("tag")
    private MessageTag tag;

    public MessagingType getMessagingType() {
        return messagingType;
    }

    public void setMessagingType(MessagingType messagingType) {
        this.messagingType = messagingType;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getSenderAction() {
        return senderAction;
    }

    public void setSenderAction(String senderAction) {
        this.senderAction = senderAction;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public MessageTag getTag() {
        return tag;
    }

    public void setTag(MessageTag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private MessagingType messagingType;
        private Recipient recipient;
        private Message message;
        private String senderAction;
        private NotificationType notificationType;
        private MessageTag tag;

        private Builder() {
        }

        public Builder messagingType(MessagingType messagingType) {
            this.messagingType = messagingType;
            return this;
        }

        public Builder recipient(Recipient recipient) {
            this.recipient = recipient;
            return this;
        }

        public Builder message(Message message) {
            this.message = message;
            return this;
        }

        public Builder senderAction(String senderAction) {
            this.senderAction = senderAction;
            return this;
        }

        public Builder notificationType(NotificationType notificationType) {
            this.notificationType = notificationType;
            return this;
        }

        public Builder tag(MessageTag tag) {
            this.tag = tag;
            return this;
        }

        public SendApiRequest build() {
            SendApiRequest sendApiRequest = new SendApiRequest();
            sendApiRequest.setMessagingType(messagingType);
            sendApiRequest.setRecipient(recipient);
            sendApiRequest.setMessage(message);
            sendApiRequest.setSenderAction(senderAction);
            sendApiRequest.setNotificationType(notificationType);
            sendApiRequest.setTag(tag);
            return sendApiRequest;
        }
    }
}
