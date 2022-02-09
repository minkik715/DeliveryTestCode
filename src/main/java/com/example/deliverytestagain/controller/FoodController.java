package com.example.deliverytestagain.controller;

import com.example.deliverytestagain.domain.Food;
import com.example.deliverytestagain.dto.FoodRequestDto;
import com.example.deliverytestagain.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFood(@PathVariable Long restaurantId,@RequestBody List<FoodRequestDto> foodRequestDtoList){
        foodService.registerFood(restaurantId, foodRequestDtoList);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getFoods(@PathVariable Long restaurantId){
        return foodService.getFoods(restaurantId);
    }
}
