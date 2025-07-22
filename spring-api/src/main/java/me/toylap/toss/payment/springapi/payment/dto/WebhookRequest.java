package me.toylap.toss.payment.springapi.payment.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebhookRequest {
    private String status;
    private String orderId;
    private String paymentKey;
    private String approvedAt;
    private String lastTransactionKey;
    private int amount;
    private String requestedAt;
}