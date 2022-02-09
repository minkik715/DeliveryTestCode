package com.example.deliverytestagain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RestaurantRequestDto {
    private String name;
    private int minOrderPrice;
    private int deliveryFee;
}
