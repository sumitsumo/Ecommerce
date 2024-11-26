package com.java.paymentservice.paymentGateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewaySelectionStrategy
{
    @Autowired
    private RazorpayPaymentGateway razorpayPaymentGateway;
    @Autowired
    private StripePaymentGateway stripePaymentGateway;

    public PaymentGateway getPaymentGateway()
    {
        return stripePaymentGateway;
    }
}
