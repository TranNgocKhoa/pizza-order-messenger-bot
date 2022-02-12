package com.khoa.bot.connector.facebook.adapter.model;

import com.khoa.bot.connector.facebook.adapter.enums.AttachmentType;

public class Attachment extends AbstractBaseModel {
    private AttachmentType type;
    private AttachmentPayload payload;

    public AttachmentType getType() {
        return type;
    }

    public void setType(AttachmentType type) {
        this.type = type;
    }

    public AttachmentPayload getPayload() {
        return payload;
    }

    public void setPayload(AttachmentPayload payload) {
        this.payload = payload;
    }

    public static Attachment fromImage(FileAttachmentPayload image) {
        Attachment attachment = new Attachment();
        attachment.setPayload(image);
        attachment.setType(AttachmentType.IMAGE);

        return attachment;
    }

}