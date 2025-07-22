package me.toylap.toss.payment.springapi.payment.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Toss 에서 받을 response 리스트
 */
@Getter
@Setter
public class TossPaymentResponse {
    private String paymentKey;
    private String orderId;
    private String orderName;
    private String status;
    private String method;
    private Integer totalAmount;
    private String approvedAt;
    private Receipt receipt;
}

@Getter
@Setter
class Receipt {
    private String url;
}
