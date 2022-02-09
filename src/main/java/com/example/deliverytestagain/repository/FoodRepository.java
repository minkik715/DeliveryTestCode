package com.example.deliverytestagain.repository;

import com.example.deliverytestagain.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food,Long> {
    List<Food> findAllByRestaurantId(Long restaurantId);
}
