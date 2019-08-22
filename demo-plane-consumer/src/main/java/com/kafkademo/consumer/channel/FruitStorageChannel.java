package com.kafkademo.consumer.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface FruitStorageChannel {

    String INPUT = "fruitStorageExportChannel";

    @Input
    MessageChannel fruitStorageExportChannel();
}

