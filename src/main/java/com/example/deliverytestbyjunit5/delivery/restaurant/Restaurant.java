package com.example.deliverytestbyjunit5.delivery.restaurant;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
public class Restaurant {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer minOrderPrice;

    @Column(nullable = false)
    private Integer deliveryFee;

    public Restaurant(RestaurantData restaurantData) {
        this.name = restaurantData.getName();
        this.minOrderPrice = restaurantData.getMinOrderPrice();
        this.deliveryFee = restaurantData.getDeliveryFee();
    }

    public Restaurant() {

    }
}
