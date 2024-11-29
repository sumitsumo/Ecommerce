package com.java.paymentservice.services;

import com.java.paymentservice.paymentGateways.PaymentGateway;
import com.java.paymentservice.paymentGateways.PaymentGatewaySelectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService
{
    @Autowired
    private PaymentGatewaySelectionStrategy paymentGatewaySelectionStrategy;
    public String getPaymentLink(Long amount,String orderId,String phoneNumber,String name)
    {
        PaymentGateway paymentGateway = paymentGatewaySelectionStrategy.getPaymentGateway();
        return paymentGateway.getPaymentLink(amount,orderId,phoneNumber,name);
    }
}
