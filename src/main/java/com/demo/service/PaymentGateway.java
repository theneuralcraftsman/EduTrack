package com.demo.service;


import org.springframework.stereotype.Component;

import com.demo.dto.PaymentLinkRequestDto;
import com.demo.entity.PaymentStatus;

@Component
public interface PaymentGateway {
    String createPaymentLink(PaymentLinkRequestDto paymentLinkRequestDto);
    PaymentStatus getStatus(String paymentId, String orderId);
}
