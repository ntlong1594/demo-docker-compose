package com.kafkademo.producer.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface FruitStorageChannel {

    String OUTPUT = "fruitStorageImportChannel";
    @Output
    MessageChannel fruitStorageImportChannel();
}

