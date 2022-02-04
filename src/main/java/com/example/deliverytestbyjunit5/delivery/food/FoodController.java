package com.example.deliverytestbyjunit5.delivery.food;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {

    private final FoodService foodService;

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public List<Food> registerFood(@PathVariable Long restaurantId, @RequestBody List<FoodData> foodDataList) {
        List<Food> foodList = new ArrayList<>();
        for (FoodData foodData : foodDataList) {
            foodList.add(foodService.registerFood(restaurantId, foodData));

        }
        return null;
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> findAllFood(@PathVariable Long restaurantId){
        return foodService.findAllFood(restaurantId);
    }
}