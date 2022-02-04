package com.example.deliverytestbyjunit5.delivery.restaurant;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
public class RestaurantData {

    private String name;

    private Integer minOrderPrice;

    private Integer deliveryFee;

}
