package com.kafkademo.producer.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OrderStorageChannel {

    String OUTPUT = "orderStorageImportChannel";
    @Output
    MessageChannel orderStorageImportChannel();
}

