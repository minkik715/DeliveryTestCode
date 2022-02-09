package com.example.deliverytestagain.dto;

import com.example.deliverytestagain.domain.OrderInfo;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDto {
    private String restaurantName;
    private List<FoodOrderDto> foods = new ArrayList<>();
    private int deliveryFee;
    private int totalPrice;

    public OrderDto(OrderInfo orderInfo){
        this.restaurantName = orderInfo.getRestaurantName();
        this.deliveryFee = orderInfo.getDeliveryFee();
        this.totalPrice = orderInfo.getTotalPrice();
    }

}