package com.kafkademo.producer.service;

import com.kafkademo.producer.channel.OrderStorageChannel;
import com.kafkademo.producer.dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
@Slf4j
public class ProducerService {
    @Autowired
    private OrderStorageChannel orderStorageChannel;

    public boolean produceToKafka(Order order) {
        MessageChannel channel = orderStorageChannel.orderStorageImportChannel();
        Message<Order> fruitMessage = MessageBuilder.withPayload(order)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build();
        if (channel.send(fruitMessage, 1)) {
            log.info("\n----------------------------------------------------------\n\t" +
                            "[Your order has been collected]: \n\t" +
                            "- Fruit Type: \t\t{}\n\t" +
                            "- Quantity: \t{}\n\t" +
                            "- User: \t{}\n----------------------------------------------------------",
                    order.getFruitType(), order.getQuantity(), order.getUser());
            return true;
        }
        log.error("======= Fail to collect your order  =======");
        return false;
    }

    public boolean produceToKafka(Integer partition, Order order) {
        log.info(" ======= Start importing fruit into Fruit Storage to partition {} =======", partition);
        MessageChannel channel = orderStorageChannel.orderStorageImportChannel();
        Message<Order> fruitMessage = MessageBuilder.withPayload(order)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build();
        if (channel.send(fruitMessage, partition == null ? 0 : partition)) {
            log.info("\n----------------------------------------------------------\n\t" +
                            "[Your order has been collected and put into partition {}]: \n\t" +
                            "- Fruit Type: \t\t{}\n\t" +
                            "- Quantity: \t{}\n\t" +
                            "- User: \t{}\n----------------------------------------------------------",
                    partition, order.getFruitType(), order.getQuantity(), order.getUser());
            return true;
        }
        log.error("======= FAIL collect your order to partition {} =======", partition);
        return false;
    }
}
