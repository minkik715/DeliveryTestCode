package com.example.deliverytestbyjunit5.delivery.order;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderRequestDto {

    List<Foods> foods = new ArrayList<>();
    Long restaurantId;
}
