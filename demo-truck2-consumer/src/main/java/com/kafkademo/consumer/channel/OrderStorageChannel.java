package com.kafkademo.consumer.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface OrderStorageChannel {

    String INPUT = "orderStorageDeliveryChannel";

    @Input
    MessageChannel orderStorageDeliveryChannel();
}

