package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.PaymentLinkRequestDto;
import com.demo.entity.PaymentDetails;
import com.demo.entity.PaymentStatus;
import com.demo.repository.PaymentRepository;

import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentGateway paymentGateway;
    private final PaymentRepository paymentRepository;
    private final EmailSenderService emailService; // Add EmailService as a dependency

    @Autowired
    public PaymentService(PaymentGateway paymentGateway, PaymentRepository paymentRepository, EmailSenderService emailService) {
        this.paymentGateway = paymentGateway;
        this.paymentRepository = paymentRepository;
        this.emailService = emailService; // Initialize EmailService
    }

    public String createLink(String orderId) {
        /*
        Make a call to order service and get the order details.
        OrderDetail order = restTemplate.getMapping(orderId)
        name = order.getCustomerName()
        amount = order.getAmount()
        phone = order.getCustomerPhone()
        */
        PaymentLinkRequestDto paymentLinkRequestDto = new PaymentLinkRequestDto();
        paymentLinkRequestDto.setOrderId(orderId);
        paymentLinkRequestDto.setCustomerName("Sachin");         //===============================================
        paymentLinkRequestDto.setPhone("6207590023");            //===============================================
        paymentLinkRequestDto.setAmount(50000);                  //===============================================

        // Generate payment link using the payment gateway
        String paymentLink = paymentGateway.createPaymentLink(paymentLinkRequestDto);

        // Save payment details in the repository
        PaymentDetails paymentResponse = new PaymentDetails();
        
        paymentResponse.setPaymentLink(paymentLink);
        paymentResponse.setOrderId(orderId);
        
        paymentResponse.setCustomerName(paymentLinkRequestDto.getCustomerName()); 
        paymentResponse.setCustomerEmail("shouryaraj24new@gmail.com");          //=============================================================
        //paymentResponse.setAmount(paymentLinkRequestDto.getAmount()); // Set amount //--------------------------------------

        
        paymentRepository.save(paymentResponse);

        return paymentLink;
    }

    public PaymentStatus getPaymentStatus(String paymentId, String orderId) {
        // Retrieve payment details by order ID
        Optional<PaymentDetails> paymentDetails = paymentRepository.findByOrderId(orderId);

        if (paymentDetails.isEmpty()) {
            throw new RuntimeException("Payment not found");
        }

        // Get payment status from the payment gateway
        PaymentStatus status = paymentGateway.getStatus(paymentId, orderId);

        // Update and save payment details with the new status
        PaymentDetails paymentResponse = paymentDetails.get();
        paymentResponse.setStatus(status);
        paymentResponse.setPaymentId(paymentId);
        paymentRepository.save(paymentResponse);

        // Send confirmation email if the payment is successful
        if (status == PaymentStatus.SUCCESS) {
            /*
        	String to = paymentResponse.getCustomerEmail(); // Assuming customer email is stored
            String subject = "Payment Confirmation";
            String text = "Dear " + paymentResponse.getCustomerName() + ",\n\nYour payment of " 
                          + paymentResponse.getAmount() + " was successful. Thank you for your purchase!";
            emailService.sendSimpleEmail(to, subject, text);
            */
            String to = paymentResponse.getCustomerEmail(); 
            String subject = "Payment Confirmation";
            String text = "Dear " + paymentResponse.getCustomerName() + ",\n\nYour payment of " 
                          + "Rs 500 "+" was successful. Thank you for your purchase!";
            emailService.sendSimpleEmail(to, subject, text);
        }

        return status;
    }
}
