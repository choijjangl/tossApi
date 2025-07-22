package me.toylap.toss.payment.springapi.payment.dto;


import lombok.Getter;
import lombok.Setter;

/**
* Toss에 보낼 값
* */
@Getter
@Setter
public class PaymentConfirmRequest {
    private String paymentKey;
    private String orderId;
    private Integer amount;
}