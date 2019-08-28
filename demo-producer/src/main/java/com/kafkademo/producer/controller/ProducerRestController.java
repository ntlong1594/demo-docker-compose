package com.kafkademo.producer.controller;

import com.kafkademo.producer.dto.Order;
import com.kafkademo.producer.service.ProducerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProducerRestController {
    @Autowired
    private ProducerService producerService;

    @PostMapping("/producer/order")
    @ApiOperation(value = "Publish fruit into storage with random partition")
    public ResponseEntity<Object> collectOrder(@RequestBody Order order) {
        if (producerService.produceToKafka(order)) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        return new ResponseEntity<>("Unable to produce fruit to kafka", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/producer/order/partition/{partition}")
    @ApiOperation(value = "Publish fruit into storage with specific partition")
    public ResponseEntity<Object> collectOrderWithPartition(@PathVariable("partition") Integer partition, @RequestBody Order order) {
        if (producerService.produceToKafka(partition, order)) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        return new ResponseEntity<>("Unable to produce fruit to kafka", HttpStatus.BAD_REQUEST);
    }
}
