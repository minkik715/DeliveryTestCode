package com.example.deliverytestbyjunit5.delivery.order;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class OrderResponseDto {
    private String restaurantName;
    private List<foodSet> foods;
    private Integer deliveryFee;

    public OrderResponseDto(String restaurantName, List<foodSet> foodSets, Integer deliveryFee, Integer totalPrice) {
        this.restaurantName = restaurantName;
        this.foods = foodSets;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
    }

    private Integer totalPrice;
}
