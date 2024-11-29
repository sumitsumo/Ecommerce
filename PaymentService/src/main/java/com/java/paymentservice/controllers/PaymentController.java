package com.java.paymentservice.controllers;

import com.java.paymentservice.dtos.IntitiatePaymentDTO;
import com.java.paymentservice.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController
{
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment")
    private String initiatePayment(@RequestBody IntitiatePaymentDTO intitiatePaymentDTO)
    {
        return paymentService
                .getPaymentLink(intitiatePaymentDTO.getAmount(),intitiatePaymentDTO.getOrderId(),intitiatePaymentDTO.getPhoneNumber(),intitiatePaymentDTO.getName());
    }
}
