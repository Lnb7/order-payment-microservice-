package com.orderService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orderService.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer> {

}
