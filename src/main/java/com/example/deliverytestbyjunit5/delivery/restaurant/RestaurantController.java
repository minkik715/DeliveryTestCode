package com.example.deliverytestbyjunit5.delivery.restaurant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("/restaurant/register")
    public Restaurant registerRestaurant(@RequestBody RestaurantData restaurantData) throws IOException {

        Restaurant restaurant = restaurantService.registerRestaurant(restaurantData);

        return restaurant;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> findAllRestaurant(){
        return restaurantService.findAllRestaurant();
    }


}
