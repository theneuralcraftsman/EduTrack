package com.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.PaymentDetails;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentDetails,Long> {
    Optional<PaymentDetails> findByOrderId(String orderId);
}
