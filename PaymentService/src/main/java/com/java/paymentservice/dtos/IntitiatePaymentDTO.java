package com.java.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntitiatePaymentDTO
{
    Long amount;
    String orderId;
    String phoneNumber;
    String name;
}
