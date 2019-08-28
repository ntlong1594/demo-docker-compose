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
        log.info(" ======= Start collecting your order and put it into Order Storage =======");
        MessageChannel channel = orderStorageChannel.orderStorageImportChannel();
        Message<Order> fruitMessage = MessageBuilder.withPayload(order)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build();
        if (channel.send(fruitMessage, 1)) {
            log.info(" ======= Success collect your order =======");
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
            log.info(" ======= Success collect your order to partition {} =======", partition);
            return true;
        }
        log.error("======= FAIL collect your order to partition {} =======", partition);
        return false;
    }
}
