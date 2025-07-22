package me.toylap.toss.payment.springapi.payment.controller;

import lombok.RequiredArgsConstructor;
import me.toylap.toss.payment.springapi.payment.dto.PaymentConfirmRequest;
import me.toylap.toss.payment.springapi.payment.dto.TossPaymentResponse;
import me.toylap.toss.payment.springapi.payment.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/*
 * 결제 API
 * */
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentContoller {

    private final PaymentService paymentService;

    @PostMapping("/confirm")
    public ResponseEntity<TossPaymentResponse> confirmPayment(@RequestBody PaymentConfirmRequest request) {
        TossPaymentResponse response = paymentService.confirmPayment(request);
        return ResponseEntity.ok(response);
    }
}