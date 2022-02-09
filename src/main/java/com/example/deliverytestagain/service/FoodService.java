package com.example.deliverytestagain.service;

import com.example.deliverytestagain.domain.Food;
import com.example.deliverytestagain.domain.Restaurant;
import com.example.deliverytestagain.dto.FoodRequestDto;
import com.example.deliverytestagain.repository.FoodRepository;
import com.example.deliverytestagain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;
    public void registerFood(Long restaurantId, List<FoodRequestDto> foodRequestDtoList) {
        for(int i =0; i<foodRequestDtoList.size()-1; i++){
            FoodRequestDto food = foodRequestDtoList.get(i);
            for(int j=i+1; j<foodRequestDtoList.size(); j++ ){
                if(food.getName().equals(foodRequestDtoList.get(j).getName())){
                    argumentError("같은 이름의 메뉴를 동시에 넣을 수 없습니다.");
                }
            }
        }
        for (FoodRequestDto foodRequestDto : foodRequestDtoList) {
            List<Food> findAllByRestaurantId = foodRepository.findAllByRestaurantId(restaurantId);
            String name = foodRequestDto.getName();
            int price = foodRequestDto.getPrice();

            for (Food food : findAllByRestaurantId) {
                if (food.getName().equals(name)) {
                    log.error("이미 이름이 같은 음식이 있습니다:{},{}", food.getName(),name);
                    argumentError("이미 이름이 같은 음식이 있습니다.");
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

            Food food = new Food(foodRequestDto);
            Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                    ()-> new IllegalArgumentException("그런 음식점은 존재하지 않습니다.")
            );
            food.setRestaurant(restaurant);
            Food savefood = foodRepository.save(food);
            log.info("음식 저장 성공:{}", savefood.getName());
        }

    }

    private void argumentError(String str) {
        throw new IllegalArgumentException(str);
    }

    public List<Food> getFoods(Long restaurantId) {
        return foodRepository.findAllByRestaurantId(restaurantId);
    }
}
