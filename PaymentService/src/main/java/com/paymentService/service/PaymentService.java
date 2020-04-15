package com.paymentService.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentService.Repository.PaymentRepository;
import com.paymentService.entity.Payment;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository repository;
	
	public Payment doPaymnet(Payment paymnet) {
		paymnet.setTransactionId(UUID.randomUUID().toString() );
		paymnet.setPaymentStatus(paymentProcessing());
		return repository.save(paymnet);
	}
	
	public String paymentProcessing() {
		return new Random().nextBoolean() ? "Success" : "false";
	}

	public Payment findPaymentHistoryByOrderId(int orderId) {
	
		return repository.findByOrderId(orderId);
	}
}
