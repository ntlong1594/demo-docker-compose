package com.kafkademo.consumer.service;

import com.kafkademo.consumer.channel.OrderStorageChannel;
import com.kafkademo.consumer.dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class PointConsumerService {
    @Autowired
    private OrderStorageChannel orderStorageChannel;
    private Map<String, Integer> pointMap = new HashMap<>();

    @StreamListener(OrderStorageChannel.INPUT)
    public void consumeFruit(Message<Order> message) {
        Order order = message.getPayload();
        String user = order.getUser();
        if (!pointMap.containsKey(user)) {
            log.info("This is first time user {} buying fruit, he/she gets 1 points", user);
            pointMap.put(user, 1);
            return;
        }
        Integer currentPoint = pointMap.get(user);
        log.info("User {} currently has {} point, he/she gets 1 points for his/her purchase {}", user, currentPoint, order.getFruitType());
        pointMap.put(user, currentPoint + 1);
        log.info("User {} now has {} point", user, pointMap.get(user));
    }

}
