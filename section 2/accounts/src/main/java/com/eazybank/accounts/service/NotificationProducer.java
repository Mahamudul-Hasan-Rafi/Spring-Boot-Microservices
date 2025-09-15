package com.eazybank.accounts.service;

import org.springframework.cloud.stream.function.StreamBridge;

public class NotificationProducer {
    private final StreamBridge streamBridge;

    public NotificationProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void sendNotification(String message) {
        streamBridge.send("accountOpening-out-0", message);
    }

    public void sendNotification(String s, String email, String email1) {
        streamBridge.send("accountOpening-out-0", s);
    }
}
