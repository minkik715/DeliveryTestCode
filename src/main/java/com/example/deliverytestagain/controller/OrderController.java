package com.example.deliverytestagain.controller;

import com.example.deliverytestagain.domain.OrderInfo;
import com.example.deliverytestagain.dto.OrderDto;
import com.example.deliverytestagain.dto.OrderRequestDto;
import com.example.deliverytestagain.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order/request")
    public OrderDto registerOrder(@RequestBody OrderRequestDto orderRequestDto){
        OrderDto orderInfo = orderService.registerOrder(orderRequestDto);
        log.info("return orderInfo123={}", orderInfo);
        return orderInfo;
    }

    @GetMapping("/orders")
    public List<OrderDto> getOrderInfos(){
        return orderService.getOrderInfos();
    }
}
