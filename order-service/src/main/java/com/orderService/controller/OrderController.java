package com.orderService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderService.common.Payment;
import com.orderService.common.TransactionRequest;
import com.orderService.common.TransactionResponce;
import com.orderService.entity.Order;
import com.orderService.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService service;
	
	
	@PostMapping("/bookOrder")
	public TransactionResponce bookOrder(@RequestBody TransactionRequest request) {
		return service.saveOrder(request);
	}
}
