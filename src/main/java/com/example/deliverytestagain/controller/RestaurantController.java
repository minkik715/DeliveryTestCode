package com.example.deliverytestagain.controller;

import com.example.deliverytestagain.domain.Restaurant;
import com.example.deliverytestagain.dto.RestaurantRequestDto;
import com.example.deliverytestagain.dto.RestaurantResponseDto;
import com.example.deliverytestagain.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("/restaurant/register")
    public Restaurant registerRestaurant(@RequestBody RestaurantRequestDto restaurantRequestDto){
         return restaurantService.registerRestaurant(restaurantRequestDto);

    }

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants()
    {
        return restaurantService.getRestaurants();
    }
}
