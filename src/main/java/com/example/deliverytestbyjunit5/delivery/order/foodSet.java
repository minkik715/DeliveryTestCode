package com.example.deliverytestbyjunit5.delivery.order;

import lombok.Data;

@Data
public class foodSet {
    private String name;

    public foodSet(String name, Integer quantity, Integer price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    private Integer quantity;
    private Integer price;
}
