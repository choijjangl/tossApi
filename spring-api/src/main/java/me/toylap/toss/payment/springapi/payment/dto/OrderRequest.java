package me.toylap.toss.payment.springapi.payment.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private String orderName;
    private Integer amount;
}
