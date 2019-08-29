package com.kafkademo.consumer.service;

import com.kafkademo.consumer.channel.OrderStorageChannel;
import com.kafkademo.consumer.dto.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class PointConsumerService {
    @Autowired
    private OrderStorageChannel orderStorageChannel;

    @StreamListener(OrderStorageChannel.INPUT)
    public void consumeFruit(Message<Order> message) {
        Order order = message.getPayload();
        System.out.println(String.format("\n--------------------------------\n" +
                        "[Earning 1 point for order]: \n" +
                        "- Fruit Type: \t%s\n" +
                        "- Quantity: \t%s\n" +
                        "- User: \t%s\n--------------------------------",
                order.getFruitType(), order.getQuantity(), order.getUser()));
    }

}
