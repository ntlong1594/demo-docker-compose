package com.kafkademo.producer.service;

import com.kafkademo.producer.channel.FruitStorageChannel;
import com.kafkademo.producer.dto.Fruit;
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
    private FruitStorageChannel fruitStorageChannel;

    public boolean produceToKafka(Fruit fruit) {
        log.info(" ======= Start importing fruit into Fruit Storage =======");
        MessageChannel channel = fruitStorageChannel.fruitStorageImportChannel();
        Message<Fruit> fruitMessage = MessageBuilder.withPayload(fruit)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build();
        if (channel.send(fruitMessage, 1)) {
            log.info(" ======= Importing fruit into Fruit Storage SUCCESS =======");
            return true;
        }
        log.error("======= Importing fruit FAIL =======");
        return false;
    }

    public boolean produceToKafka(Integer partition, Fruit fruit) {
        log.info(" ======= Start importing fruit into Fruit Storage to partition {} =======", partition);
        MessageChannel channel = fruitStorageChannel.fruitStorageImportChannel();
        Message<Fruit> fruitMessage = MessageBuilder.withPayload(fruit)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build();
        if (channel.send(fruitMessage, partition == null ? 0 : partition)) {
            log.info(" ======= Importing fruit into Fruit Storage SUCCESS to partition {} =======", partition);
            return true;
        }
        log.error("======= Importing fruit FAIL to partition {} =======", partition);
        return false;
    }
}
