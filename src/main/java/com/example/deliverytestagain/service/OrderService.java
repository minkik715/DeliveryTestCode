package com.example.deliverytestagain.service;

import com.example.deliverytestagain.domain.Food;
import com.example.deliverytestagain.domain.OrderInfo;
import com.example.deliverytestagain.domain.Orders;
import com.example.deliverytestagain.domain.Restaurant;
import com.example.deliverytestagain.dto.FoodOrderDto;
import com.example.deliverytestagain.dto.FoodsRequestDto;
import com.example.deliverytestagain.dto.OrderDto;
import com.example.deliverytestagain.dto.OrderRequestDto;
import com.example.deliverytestagain.repository.FoodRepository;
import com.example.deliverytestagain.repository.OrderInfoRepository;
import com.example.deliverytestagain.repository.OrdersRepository;
import com.example.deliverytestagain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderInfoRepository orderInfoRepository;
    private final OrdersRepository ordersRepository;


    @Transactional
    public OrderDto registerOrder(OrderRequestDto orderRequestDto) {
        List<FoodsRequestDto> foods = orderRequestDto.getFoods();
        Long restaurantId = orderRequestDto.getRestaurantId();
        List<Orders> ordersList = new ArrayList<>();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                ()-> new NullPointerException("그런 음식점은 존재하지 않습니다.")
        );
        int totalPrice = 0;
        for (FoodsRequestDto food : foods) {
            int quantity = food.getQuantity();
            if(!(quantity>=1 && quantity<=100)){
                throw new IllegalArgumentException("수량은 1과 100의 사이여야 합니다!");
            }

            Long id = food.getId();
            Food findFood = foodRepository.findById(id).orElseThrow(
                    ()-> new NullPointerException("그런 음식은 존재하지 않습니다.")
            );
            String name = findFood.getName();
            int price = findFood.getPrice() * quantity;
            final Orders orders = new Orders();
            orders.setName(name);
            orders.setPrice(price);
            orders.setQuantity(quantity);

            ordersList.add(ordersRepository.save(orders));
            totalPrice+= price;
        }
        if(restaurant.getMinOrderPrice() > totalPrice){
            log.error("최소 금액보다 총 가격이 낮습니다:최소금액:{} 총가격:{}",restaurant.getMinOrderPrice(), totalPrice);
            throw new IllegalArgumentException("최소 금액보다 총 가격이 낮습니다!");
        }
        totalPrice+= restaurant.getDeliveryFee();
        final OrderInfo orderInfo = new OrderInfo(restaurant.getDeliveryFee(), totalPrice, restaurant.getName());
        for (Orders orders : ordersList) {
            orderInfo.addOrders(orders);
        }
        OrderInfo save = orderInfoRepository.save(orderInfo);
        for (Orders orders : ordersList) {
            Orders order = ordersRepository.findById(orders.getId()).orElseThrow( () -> new NullPointerException("빈 객체입니다."));
            order.setOrderInfo(save);
        }

        OrderInfo orderInfo1 = orderInfoRepository.findById(save.getId()).orElse(null);

        OrderDto orderDto = new OrderDto(orderInfo1);
        List<Orders> foods1 = orderInfo1.getFoods();
        for (Orders orders : foods1) {
            FoodOrderDto foodOrderDto = new FoodOrderDto(orders);
            orderDto.getFoods().add(foodOrderDto);
        }

        return orderDto;

    }

    @Transactional
    public List<OrderDto> getOrderInfos() {
        List<OrderInfo> all = orderInfoRepository.findAll();
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (OrderInfo orderInfo : all) {
            OrderDto orderDto = new OrderDto(orderInfo);
            List<Orders> foods1 = orderInfo.getFoods();
            for (Orders orders : foods1) {
                FoodOrderDto foodOrderDto = new FoodOrderDto(orders);
                orderDto.getFoods().add(foodOrderDto);
            }
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }
}
