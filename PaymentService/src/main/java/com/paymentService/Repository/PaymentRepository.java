package com.paymentService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymentService.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	Payment findByOrderId(int orderId);

}
