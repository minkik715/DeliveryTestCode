package com.example.deliverytestagain.repository;

import com.example.deliverytestagain.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
