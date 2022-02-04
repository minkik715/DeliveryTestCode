package com.example.deliverytestbyjunit5.delivery.order;

import com.example.deliverytestbyjunit5.delivery.food.Food;
import com.example.deliverytestbyjunit5.delivery.food.FoodRepository;
import com.example.deliverytestbyjunit5.delivery.restaurant.Restaurant;
import com.example.deliverytestbyjunit5.delivery.restaurant.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Long restaurantId = orderRequestDto.getRestaurantId();
        Restaurant deliverRestaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new IllegalArgumentException("그런 식당은 존재하지 않습니다.")
        );
        Integer deliveryFee = deliverRestaurant.getDeliveryFee();
        String name = deliverRestaurant.getName();
        List<Foods> foods = orderRequestDto.getFoods();
        List<foodSet> foodSets = new ArrayList<>();

        Integer totalPrice = 0;
        for (Foods food : foods) {
            Long id = food.getId();
            Integer quantity = food.getQuantity();

            if(!(quantity>=1 && quantity<=100)){
                throw new IllegalArgumentException("수량은 1과 100의 사이여야 합니다!");
            }
            Food findFood = foodRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("그런 음식은 존재하지 않습니다.")
            );
            int eachTotalPrice = findFood.getPrice() * quantity;
            foodSets.add(new foodSet(findFood.getName(), quantity,eachTotalPrice));
            Integer price = findFood.getPrice();
            totalPrice += price * quantity;
            Order2 order2 = new Order2(findFood.getName(), quantity, eachTotalPrice);
            orderRepository.save(order2);
        }
        if(deliverRestaurant.getMinOrderPrice() > totalPrice){
            log.error("최소 금액보다 총 가격이 낮습니다:최소금액:{} 총가격:{}",deliverRestaurant.getMinOrderPrice(), totalPrice);
            throw new IllegalArgumentException("최소 금액보다 총 가격이 낮습니다!");
        }
        totalPrice +=deliveryFee;
        log.info("totalPrice={}",totalPrice);
        log.info("deliveryFee={}",deliveryFee);
        log.info("name={}",name);
        OrderResponseDto orderResponseDto = new OrderResponseDto(name, foodSets, deliveryFee, totalPrice);
        orderResponseDtoList.add(orderResponseDto);
        return orderResponseDto;
    }

    public List<OrderResponseDto> findAllOrder() {
        return orderResponseDtoList;

    }
}
