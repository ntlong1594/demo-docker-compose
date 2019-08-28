package com.kafkademo.consumer.service;

import com.kafkademo.consumer.channel.OrderStorageChannel;
import com.kafkademo.consumer.dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Truck2ConsumerService {
    @Autowired
    private OrderStorageChannel orderStorageChannel;

    @StreamListener(OrderStorageChannel.INPUT)
    public void consumeFruit(Message<Order> message) {
        Order order = message.getPayload();
        log.info("===== '{}' is shipping to end-user with name: '{}' by TRUCK 2 consumer =====", order.getFruitType(), order.getUser());
    }

}
