package com.example.deliverytestbyjunit5.delivery.food;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food>  findAllByRestaurantId(Long restaurantId);
}
