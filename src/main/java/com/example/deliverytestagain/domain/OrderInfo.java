package com.example.deliverytestagain.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Setter
@Getter
public class OrderInfo {
    @Id
    @Column(name = "order_info_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int deliveryFee;
    private int totalPrice;
    private String restaurantName;

    @OneToMany
    private List<Orders> foods = new ArrayList<>();

    public OrderInfo(int deliveryFee, int totalPrice, String restaurantName) {
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
        this.restaurantName = restaurantName;
    }

    public void addOrders(Orders orders){
        this.foods.add(orders);
    }

    public OrderInfo() {

    }
}
