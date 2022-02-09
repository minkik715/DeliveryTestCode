package com.example.deliverytestagain.dto;

import com.example.deliverytestagain.domain.Orders;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FoodOrderDto {
    String name;
    int quantity;
    int price;

    public FoodOrderDto(Orders orders){
        this.name = orders.getName();
        this.quantity = orders.getQuantity();
        this.price = orders.getPrice();
    }
}
