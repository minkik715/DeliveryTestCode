package com.example.deliverytestbyjunit5.delivery.food;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class FoodService {

    private final FoodRepository foodRepository;

    public Food registerFood(Long restaurantId, FoodData foodData) {
        String name = foodData.getName();
        Integer price = foodData.getPrice();
        log.info("Food의 정보 name={}, price={}, restaurantId={}", foodData.getName(), foodData.getPrice(), restaurantId);

        List<Food> findAllByRestaurantId = foodRepository.findAllByRestaurantId(restaurantId);
        for (Food food : findAllByRestaurantId) {
            log.info("food in {} -> {}", food.getRestaurantId(), food );
            if(food.getName().equals(foodData.getName()) ){
                log.error("이미 이름이 같은 음식이 있습니다:{}", food.getName());
                argumentError("이미 이름이 같은 음식이 있습니다.");
                break;
            }
        }

        if(!(price >= 100 && price <=1000000 )){
            log.error("음식 가격은 1,00~1000,000원사이여야 합니다.");
            argumentError("음식 가격은 100~1,000,000원사이여야 합니다.");

        }
        if(price%100 != 0){
            log.error("음식 가격은 100원 단위로 입력해주세요!");
            argumentError("음식 가격은 100원 단위로 입력해주세요!");

        }
        Food food = new Food(foodData, restaurantId);
        Food savefood = foodRepository.save(food);
        log.info("음식 저장 성공:{}", savefood);
        return savefood;
    }

    private void argumentError(String str) {
        throw new IllegalArgumentException(str);
    }

    public List<Food> findAllFood(Long restaurantId) {
        return foodRepository.findAllByRestaurantId(restaurantId);
    }
}
