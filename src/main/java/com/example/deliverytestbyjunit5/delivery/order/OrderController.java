package com.example.deliverytestbyjunit5.delivery.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order/request")
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequestDto){
        log.info("orderRequestDto={}",orderRequestDto);
        return orderService.createOrder(orderRequestDto);
    }

    @GetMapping("/orders")
    public  List<OrderResponseDto> findAllOrder(){
        return orderService.findAllOrder();
    }
}
