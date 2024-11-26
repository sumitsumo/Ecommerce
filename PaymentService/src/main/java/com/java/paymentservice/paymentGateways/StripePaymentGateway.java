package com.java.paymentservice.paymentGateways;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements PaymentGateway
{
    @Value("${stripe.apiKey}")
    private String stripeSecretKey;

    @Override
    public String getPaymentLink(Long amount, String orderId, String phoneNumber, String name)
    {
        Stripe.apiKey = this.stripeSecretKey;
        Price price = getPrice(amount*100L);
        try
        {
            PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        ).setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder()
                                .setType(PaymentLinkCreateParams.AfterCompletion.Type.HOSTED_CONFIRMATION)
                                .setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder().build()).build())
                        .build();


            PaymentLink paymentLink = PaymentLink.create(params);
            paymentLink.setId(orderId);

            return paymentLink.getUrl();
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }
    public Price getPrice(Long amount)
    {
        try
        {
            PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(amount)
                        .setRecurring(
                                PriceCreateParams.Recurring.builder()
                                        .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                        .build()
                        )
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                        )
                        .build();
            return Price.create(params);
        } catch (StripeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
