package com.kafkademo.consumer.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Fruit {
    private String name;
    private String inOrgin;
    private Date importDate;
}
