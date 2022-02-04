package com.example.deliverytestbyjunit5.delivery.food;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long restaurantId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    public Food(FoodData foodData, Long restaurantId) {
        this.restaurantId = restaurantId;
        this.name = foodData.getName();
        this.price = foodData.getPrice();
    }
}
