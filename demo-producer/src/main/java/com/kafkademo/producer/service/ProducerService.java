package com.kafkademo.producer.service;

import com.kafkademo.producer.channel.OrderStorageChannel;
import com.kafkademo.producer.dto.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class ProducerService {
    @Autowired
    private OrderStorageChannel orderStorageChannel;

    public boolean produceToKafka(Order order) {
        MessageChannel channel = orderStorageChannel.orderStorageImportChannel();
        Message<Order> fruitMessage = MessageBuilder.withPayload(order)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build();
        if (channel.send(fruitMessage, 1)) {
            System.out.println(String.format("\n--------------------------------\n" +
                            "[Your order has been collected]: \n" +
                            "- Fruit Type: \t%s\n" +
                            "- Quantity: \t%s\n" +
                            "- User: \t%s\n--------------------------------",
                    order.getFruitType(), order.getQuantity(), order.getUser()));
            return true;
        }
        System.out.println("Fail to connect kafka");
        return false;
    }

    public boolean produceToKafka(Integer partition, Order order) {
        MessageChannel channel = orderStorageChannel.orderStorageImportChannel();
        Message<Order> fruitMessage = MessageBuilder.withPayload(order)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build();
        if (channel.send(fruitMessage, partition == null ? 0 : partition)) {
            System.out.println(String.format("\n--------------------------------\n" +
                            "[Your order has been collected]: \n" +
                            "- Fruit Type: \t%s\n" +
                            "- Quantity: \t%s\n" +
                            "- User: \t%s\n--------------------------------",
                    order.getFruitType(), order.getQuantity(), order.getUser()));
            return true;
        }
        return false;
    }
}
