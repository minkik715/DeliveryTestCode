package com.example.deliverytestagain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class OrderRequestDto {

    private Long restaurantId;
    List<FoodsRequestDto> foods;

}
