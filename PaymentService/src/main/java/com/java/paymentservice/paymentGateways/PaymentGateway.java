package com.java.paymentservice.paymentGateways;

import com.razorpay.RazorpayException;

public interface PaymentGateway
{
    String getPaymentLink(Long amount,String orderId,String phoneNumber,String name);
}
