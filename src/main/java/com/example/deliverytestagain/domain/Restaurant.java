package com.example.deliverytestagain.domain;

import com.example.deliverytestagain.dto.RestaurantRequestDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Restaurant {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "restaurant_id")
    private Long id;


    private String name;
    private int minOrderPrice;
    private int deliveryFee;

    public Restaurant(RestaurantRequestDto restaurantRequestDto){
        this.name = restaurantRequestDto.getName();
        this.deliveryFee = restaurantRequestDto.getDeliveryFee();
        this.minOrderPrice = restaurantRequestDto.getMinOrderPrice();
    }

    public Restaurant() {

    }
}
