package com.kafkademo.producer.dto;

import lombok.Data;

@Data
public class Order {
    private String fruitType;
    private Integer quantity;
    private String user;
}
