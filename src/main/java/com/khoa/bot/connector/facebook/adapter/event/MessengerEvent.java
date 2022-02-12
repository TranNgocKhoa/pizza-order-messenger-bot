package com.khoa.bot.connector.facebook.adapter.event;

import com.khoa.bot.connector.facebook.adapter.event.content.message.MessageContent;
import com.khoa.bot.connector.facebook.adapter.event.content.message.PostbackContent;
import com.khoa.bot.connector.facebook.adapter.model.Recipient;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MessengerEvent {
    private Sender sender;
    private Recipient recipient;
    private long timestamp;
    private MessageContent message;
    private PostbackContent postback;

    public MessageContent getMessage() {
        return message;
    }

    public void setMessage(MessageContent message) {
        this.message = message;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public PostbackContent getPostback() {
        return postback;
    }

    public void setPostback(PostbackContent postback) {
        this.postback = postback;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
