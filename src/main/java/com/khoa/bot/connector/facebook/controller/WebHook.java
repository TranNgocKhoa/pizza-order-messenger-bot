package com.khoa.bot.connector.facebook.controller;

import com.khoa.bot.connector.facebook.adapter.enums.MessagingType;
import com.khoa.bot.connector.facebook.adapter.event.IncomingEventEntry;
import com.khoa.bot.connector.facebook.adapter.event.MessengerEvent;
import com.khoa.bot.connector.facebook.adapter.event.MessengerIncomingEvent;
import com.khoa.bot.connector.facebook.adapter.event.Sender;
import com.khoa.bot.connector.facebook.adapter.event.content.message.MessageContent;
import com.khoa.bot.connector.facebook.adapter.event.content.message.PostbackContent;
import com.khoa.bot.connector.facebook.adapter.event.content.message.QuickReplyContent;
import com.khoa.bot.connector.facebook.adapter.event.nlp.NlpData;
import com.khoa.bot.connector.facebook.adapter.model.Recipient;
import com.khoa.bot.connector.facebook.adapter.request.SendApiRequest;
import com.khoa.bot.connector.facebook.context.BotMessageContent;
import com.khoa.bot.connector.facebook.context.DialogContext;
import com.khoa.bot.connector.facebook.context.UserContext;
import com.khoa.bot.connector.facebook.entity.Event;
import com.khoa.bot.connector.facebook.entity.Message;
import com.khoa.bot.connector.facebook.entity.User;
import com.khoa.bot.connector.facebook.repository.mapper.EventMapper;
import com.khoa.bot.connector.facebook.send.Messenger;
import com.khoa.bot.connector.facebook.service.FlowHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/messenger")
public class WebHook {
    private final Logger LOGGER = LoggerFactory.getLogger(WebHook.class);
    private static final int SIGNATURE_LENGTH = 45;

    private final String verifyToken;
    private final Messenger messenger;
    private final FlowHandler flowHandler;
    private final DialogContext dialogContext;
    private final EventMapper eventMapper;

    public WebHook(@Value("${verifyToken}") String verifyToken, Messenger messenger, FlowHandler flowHandler, DialogContext dialogContext, EventMapper eventMapper) {
        this.verifyToken = verifyToken;
        this.messenger = messenger;
        this.flowHandler = flowHandler;
        this.dialogContext = dialogContext;
        this.eventMapper = eventMapper;
    }

    @PostMapping
    public boolean receiveMessage(@RequestBody MessengerIncomingEvent request, @RequestHeader(value = "X-Hub-Signature") String headerSignature) {
        LOGGER.info("Receive Message Event ==> {} :: X-Hub-Signature ==> {}", request, headerSignature);
        CompletableFuture.runAsync(() -> {
            IncomingEventEntry incomingEventEntry = request.getEntry().stream().findFirst().orElseThrow(() -> new RuntimeException("Invalid Message Event!"));

            Optional<MessengerEvent> messengerEvent = incomingEventEntry.getMessaging().stream().findFirst();
            final String recipientId = messengerEvent
                    .map(MessengerEvent::getSender)
                    .map(Sender::getId).orElse(StringUtils.EMPTY);
            try {
                saveEvent(incomingEventEntry);
                User userContext = this.getUserContext(incomingEventEntry);
                BotMessageContent botMessageContent = getBotMessageContent(messengerEvent);

                flowHandler.getMessage(userContext, botMessageContent)
                        .stream()
                        .map(message -> SendApiRequest.builder()
                                .message(message)
                                .recipient(Recipient.of(recipientId))
                                .messagingType(MessagingType.RESPONSE)
                                .build())
                        .forEach(messenger::send);
            } catch (Exception e) {
                LOGGER.error("Caught error, sending error message", e);
                SendApiRequest sendApiRequest = SendApiRequest.builder()
                        .message(com.khoa.bot.connector.facebook.adapter.model.Message.fromText("Hiện tại đã xảy ra lỗi..."))
                        .recipient(Recipient.of(recipientId))
                        .messagingType(MessagingType.RESPONSE)
                        .build();

                messenger.sendAsync(sendApiRequest);
            }
        });

        return true;
    }

    private BotMessageContent getBotMessageContent(Optional<MessengerEvent> messengerEvent) {
        if (messengerEvent.map(MessengerEvent::getMessage).isPresent()) {
            Optional<MessageContent> messageContent = messengerEvent.map(MessengerEvent::getMessage);
            return BotMessageContent.builder()
                    .nlpData(messageContent.map(MessageContent::getNlp).orElse(new NlpData()))
                    .payload(messageContent.map(MessageContent::getQuickReply).map(QuickReplyContent::getPayload).orElse(null))
                    .text(messageContent.map(MessageContent::getText).orElse(null))
                    .build();
        }

        if (messengerEvent.map(MessengerEvent::getPostback).isPresent()) {
            Optional<PostbackContent> postbackContent = messengerEvent.map(MessengerEvent::getPostback);
            return BotMessageContent.builder()
                    .nlpData(new NlpData())
                    .payload(postbackContent.map(PostbackContent::getPayload).orElse(null))
                    .text(postbackContent.map(PostbackContent::getTitle).orElse(null))
                    .build();
        }

        return new BotMessageContent();
    }

    private User getUserContext(IncomingEventEntry incomingEventEntry) {
        UserContext userContext = this.dialogContext.getUserContext();

        return userContext.initUser(incomingEventEntry.getMessaging()
                .stream()
                .findFirst()
                .map(MessengerEvent::getSender)
                .map(Sender::getId)
                .orElseThrow(() -> new RuntimeException("Wrong Facebook ID")));
    }

    private void saveEvent(IncomingEventEntry incomingEventEntry) {
        Event event = new Event();
        event.setId(incomingEventEntry.getId());
        event.setDateTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(incomingEventEntry.getTime()), ZoneId.of("Asia/Ho_Chi_Minh")));

        eventMapper.insertEvent(event);

        Optional<MessengerEvent> messengerEvent = incomingEventEntry.getMessaging().stream().findFirst();

        Message message = new Message();
        message.setEventId(event.getId());
        message.setContent(messengerEvent.map(MessengerEvent::getMessage).map(MessageContent::toString).orElse(null));
        message.setSenderId(messengerEvent.map(MessengerEvent::getSender).map(Sender::getId).orElse(StringUtils.EMPTY));
        message.setRecipientId(messengerEvent.map(MessengerEvent::getRecipient).map(Recipient::getId).orElse(StringUtils.EMPTY));

        eventMapper.insertMessage(message);
    }

    @GetMapping
    public String webHook(@RequestParam("hub.mode") String mode,
                          @RequestParam("hub.verify_token") String verifyToken,
                          @RequestParam("hub.challenge") String challenge) {

        if (verifyToken.equals(this.verifyToken)) {
            LOGGER.info("Mode ==> {} :: Verify Token ==> {} :: Challenge ==> {}", mode, verifyToken, challenge);
        }

        return challenge;
    }
}
