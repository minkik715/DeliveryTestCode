package com.example.deliverytestagain.repository;

import com.example.deliverytestagain.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders,Long> {

}
