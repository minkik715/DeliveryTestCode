package com.example.deliverytestagain.repository;

import com.example.deliverytestagain.domain.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {

}
