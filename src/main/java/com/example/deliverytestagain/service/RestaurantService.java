package com.example.deliverytestagain.service;

import com.example.deliverytestagain.domain.Restaurant;
import com.example.deliverytestagain.dto.RestaurantRequestDto;
import com.example.deliverytestagain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Restaurant registerRestaurant(RestaurantRequestDto restaurantRequestDto) {
        int minOrderPrice = restaurantRequestDto.getMinOrderPrice();
        int deliveryFee = restaurantRequestDto.getDeliveryFee();



        if(!(minOrderPrice >= 1000 && minOrderPrice <=100000 )){
            log.error("최소가격은 1,000~100,000원사이여야 합니다.");
            argumentError("최소가격은 1,000~100,000원사이여야 합니다.");

        }
        if(minOrderPrice%100 != 0){
            log.error("최소가격은 100원 단위로 입력해주세요!");
            argumentError("최소가격은 100원 단위로 입력해주세요!");


        }
        if(!(deliveryFee >= 0 && deliveryFee<=10000)){
            log.error("배달비용은 0~10,000원사이여야 합니다.");
            argumentError("배달비용은 0~10,000원사이여야 합니다.");

        }
        if(deliveryFee%500 != 0){
            log.error("배달비용은 500원 단위로 입력해주세요!");
            argumentError("배달비용은 500원 단위로 입력해주세요!");
            //return badRequest(response,restaurantData);
        }
        Restaurant restaurant = new Restaurant(restaurantRequestDto);

        log.info("음식점 등록 완료:{}", restaurant);

        return restaurantRepository.save(restaurant);
    }

    private void argumentError(String str) {
        throw new IllegalArgumentException(str);
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }


}
