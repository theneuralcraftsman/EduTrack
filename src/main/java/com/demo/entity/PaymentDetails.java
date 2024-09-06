package com.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PaymentDetails extends BaseModel{
    private String orderId;
    private String paymentId;
    private String PaymentLink;
    private String customerEmail;
    private String customerName;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
