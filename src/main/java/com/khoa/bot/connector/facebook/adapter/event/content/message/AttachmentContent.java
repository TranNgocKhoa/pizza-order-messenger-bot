package com.khoa.bot.connector.facebook.adapter.event.content.message;

import com.khoa.bot.connector.facebook.adapter.enums.AttachmentType;

public class AttachmentContent {
    private AttachmentType type;
    private Payload payload;

    public AttachmentType getType() {
        return type;
    }

    public void setType(AttachmentType type) {
        this.type = type;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }
}
