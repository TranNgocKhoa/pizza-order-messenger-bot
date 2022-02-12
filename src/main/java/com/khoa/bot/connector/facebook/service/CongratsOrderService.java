package com.khoa.bot.connector.facebook.service;

import com.khoa.bot.connector.facebook.adapter.model.Attachment;
import com.khoa.bot.connector.facebook.adapter.model.FileAttachmentPayload;
import com.khoa.bot.connector.facebook.adapter.model.Message;
import org.springframework.stereotype.Service;

@Service
public class CongratsOrderService {
    public Message getMessage() {
        return Message.builder()
                .attachment(Attachment.fromImage(getCongrats()))
                .build();
    }
    private FileAttachmentPayload getCongrats() {
        FileAttachmentPayload fileAttachmentPayload = new FileAttachmentPayload();
        fileAttachmentPayload.setReusable(true);
        fileAttachmentPayload.setUrl("https://res.cloudinary.com/dbqgbletk/image/upload/v1631365637/message/congrat_x6mnjy.png");

        return fileAttachmentPayload;
    }
}
