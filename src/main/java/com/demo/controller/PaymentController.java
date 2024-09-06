package com.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.PaymentStatus;
import com.demo.service.PaymentService;

@RestController
public class PaymentController {

    PaymentService paymentService;

    @Autowired
    PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/payment/createLink")
    public String createPaymentLink(@RequestParam String orderId){
        return paymentService.createLink(orderId);
    }

    @GetMapping("/payment/getPaymentStatus")
    public PaymentStatus getPaymentStatus(@RequestParam("paymentId") String paymentId, @RequestParam("orderId") String orderId){
        return paymentService.getPaymentStatus(paymentId, orderId);
    }
}
