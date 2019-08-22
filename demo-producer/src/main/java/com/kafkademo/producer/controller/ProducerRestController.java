package com.kafkademo.producer.controller;

import com.kafkademo.producer.dto.Fruit;
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

    @PostMapping("/producer/fruit")
    @ApiOperation(value = "Publish fruit into storage with random partition")
    public ResponseEntity<Object> produceFruit(@RequestBody Fruit fruit) {
        if (producerService.produceToKafka(fruit)) {
            return new ResponseEntity<>(fruit, HttpStatus.OK);
        }
        return new ResponseEntity<>("Unable to produce fruit to kafka", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/producer/fruit/partition/{partition}")
    @ApiOperation(value = "Publish fruit into storage with specific partition")
    public ResponseEntity<Object> produceFruitWithPartition(@PathVariable("partition") Integer partition, @RequestBody Fruit fruit) {
        if (producerService.produceToKafka(partition, fruit)) {
            return new ResponseEntity<>(fruit, HttpStatus.OK);
        }
        return new ResponseEntity<>("Unable to produce fruit to kafka", HttpStatus.BAD_REQUEST);
    }
}
