package com.orderService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.orderService.common.Payment;
import com.orderService.common.TransactionRequest;
import com.orderService.common.TransactionResponce;
import com.orderService.entity.Order;
import com.orderService.repository.OrderRepository;


@Service
@RefreshScope
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	@Lazy
	private RestTemplate template;
	
	@Value("${microservice.payment-service.endpoints.endpoint.uri}")
	private String ENDPOINT_URL;
	
	public TransactionResponce saveOrder(TransactionRequest request) {
		
		String response = "";
		
		Order order = request.getOrder();
		
		Payment payment = request.getPayment();
		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());
		
		Payment paymentResponse = template.postForObject(ENDPOINT_URL, payment, Payment.class);
		
		response = paymentResponse.getPaymentStatus().equals("Success") ? "Payment process succful" : "Process failure";
		
		 repository.save(order);
		
		 return new TransactionResponce(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);
	}
}
