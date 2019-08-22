package com.kafkademo.consumer.service;

import com.kafkademo.consumer.channel.FruitStorageChannel;
import com.kafkademo.consumer.dto.Fruit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PlaneConsumerService {
    @Autowired
    private FruitStorageChannel fruitStorageChannel;

    @StreamListener(FruitStorageChannel.INPUT)
    public void consumeFruit(Message<Fruit> message) {
        Fruit fruit = message.getPayload();
        log.info("===========================");
        log.info("'{}' is shipping to end-user by PLANE CONSUMER", fruit.getName());
        log.info("===========================");
    }

}
