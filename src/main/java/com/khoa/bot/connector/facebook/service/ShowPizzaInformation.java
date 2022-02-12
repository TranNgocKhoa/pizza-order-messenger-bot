package com.khoa.bot.connector.facebook.service;

import com.khoa.bot.connector.facebook.adapter.model.Attachment;
import com.khoa.bot.connector.facebook.adapter.model.FileAttachmentPayload;
import com.khoa.bot.connector.facebook.adapter.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ShowPizzaInformation {
    public List<Message> getMessage(String pizzaPayload) {
        return Stream.of(getResponseText(pizzaPayload), getImageResponse(pizzaPayload))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private Message getImageResponse(String pizzaType) {
        switch (pizzaType) {
            case "pizza_size_small":
                return Message.builder()
                        .attachment(Attachment.fromImage(getSmallPizzaImage()))
                        .build();
            case "pizza_size_medium":
                return Message.builder()
                        .attachment(Attachment.fromImage(getMediumPizzaImage()))
                        .build();

            case "pizza_size_big":
                return Message.builder()
                        .attachment(Attachment.fromImage(getBigPizzaImage()))
                        .build();
        }
        return null;
    }

    private Message getResponseText(String pizzaType) {
        switch (pizzaType) {
            case "pizza_size_small":
                return Message.fromText("Đây là pizza size nhỏ:");
            case "pizza_size_medium":
                return Message.fromText("Đây là pizza size vừa:");

            case "pizza_size_big":
                return Message.fromText("Đây là pizza size lớn:");
        }

        return null;
    }

    private FileAttachmentPayload getSmallPizzaImage() {
        FileAttachmentPayload fileAttachmentPayload = new FileAttachmentPayload();
        fileAttachmentPayload.setReusable(true);
        fileAttachmentPayload.setUrl("https://res.cloudinary.com/dbqgbletk/image/upload/v1630221349/message/pizza_size_lon_azxirk.png");

        return fileAttachmentPayload;
    }

    private FileAttachmentPayload getMediumPizzaImage() {
        FileAttachmentPayload fileAttachmentPayload = new FileAttachmentPayload();
        fileAttachmentPayload.setReusable(true);
        fileAttachmentPayload.setUrl("https://res.cloudinary.com/dbqgbletk/image/upload/v1630221323/message/pizza_size_vua_xfiuzi.jpg");

        return fileAttachmentPayload;
    }

    private FileAttachmentPayload getBigPizzaImage() {
        FileAttachmentPayload fileAttachmentPayload = new FileAttachmentPayload();
        fileAttachmentPayload.setReusable(true);
        fileAttachmentPayload.setUrl("https://res.cloudinary.com/dbqgbletk/image/upload/v1630221349/message/pizza_size_lon_azxirk.png");

        return fileAttachmentPayload;
    }
}
