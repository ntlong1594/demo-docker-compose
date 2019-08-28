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
        System.out.println(String.format("\n----------------------------------------------------------\n\t" +
                        "[Earning 1 point for order]: \n\t" +
                        "- Fruit Type: \t%s\n\t" +
                        "- Quantity: \t%s\n\t" +
                        "- User: \t%s\n----------------------------------------------------------",
                order.getFruitType(), order.getQuantity(), order.getUser()));
    }

}
