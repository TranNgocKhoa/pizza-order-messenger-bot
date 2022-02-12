package com.khoa.bot.connector.facebook.send;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khoa.bot.connector.facebook.adapter.request.SendApiRequest;
import com.khoa.bot.connector.facebook.adapter.response.SendApiResponse;
import com.khoa.bot.connector.facebook.client.MessengerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Component
public class Messenger {
    private static final Logger LOGGER = LoggerFactory.getLogger(Messenger.class);

    private final RestTemplate restTemplate;
    private final MessengerProperties messengerProperties;
    private final ObjectMapper objectMapper;

    public Messenger(RestTemplate restTemplate, MessengerProperties messengerProperties, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.messengerProperties = messengerProperties;
        this.objectMapper = objectMapper;
    }

    public SendApiResponse send(@NonNull SendApiRequest request) {
        LOGGER.debug("Sending reply ==> {} :: URL ==> {}", request, messengerProperties.getUrl());

        try {
            LOGGER.info(objectMapper.writeValueAsString(request));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            return restTemplate.postForObject(messengerProperties.getUrl(), request, SendApiResponse.class);
        } catch (HttpClientErrorException e) {
            LOGGER.error("Error ==> {}", e.getResponseBodyAsString(), e);

            return null;
        }
    }

    public void sendAsync(SendApiRequest request) {
        if (request.getMessage().getAttachment() != null) {
            send(request);
        } else {
            CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> send(request));
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                if (!voidCompletableFuture.isDone()) {
                    TimeUnit.MILLISECONDS.sleep(500);
                    if (!voidCompletableFuture.isDone()) {
                        TimeUnit.MILLISECONDS.sleep(500);
                        if (!voidCompletableFuture.isDone()) {
                            TimeUnit.MILLISECONDS.sleep(500);
                            if (!voidCompletableFuture.isDone()) {
                                TimeUnit.MILLISECONDS.sleep(500);
                                if (!voidCompletableFuture.isDone()) {
                                    TimeUnit.MILLISECONDS.sleep(500);
                                    if (!voidCompletableFuture.isDone()) {
                                        TimeUnit.MILLISECONDS.sleep(500);
                                        if (!voidCompletableFuture.isDone()) {
                                            TimeUnit.MILLISECONDS.sleep(500);
                                            if (!voidCompletableFuture.isDone()) {
                                                TimeUnit.MILLISECONDS.sleep(500);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (InterruptedException e) {
                LOGGER.info("Error when sleep");
            }
        }


    }
}
