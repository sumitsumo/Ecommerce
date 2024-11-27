package com.java.paymentservice.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/StripeWebhook")
public class StripeWebhookController
{
    @PostMapping
    public void respondToPaymentLinkCreated(@RequestBody String stripeJsonEvent)
    {
        System.out.println(stripeJsonEvent);
    }
}
