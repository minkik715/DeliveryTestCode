package com.example.deliverytestbyjunit5.delivery.order;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Order2 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    int quantity;

    public Order2(String name, int quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    @Column(nullable = false)
    int price;


    public Order2() {

    }
}
