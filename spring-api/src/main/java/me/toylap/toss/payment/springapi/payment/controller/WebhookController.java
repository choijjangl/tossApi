package me.toylap.toss.payment.springapi.payment.controller;

import lombok.RequiredArgsConstructor;
import me.toylap.toss.payment.springapi.payment.dto.WebhookRequest;
import me.toylap.toss.payment.springapi.payment.entity.Order;
import me.toylap.toss.payment.springapi.payment.repository.OrderRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


/*
* 주문 API
* */
@RestController
@RequestMapping("/api/webhook")
public class WebhookController {
    @PostMapping
    public ResponseEntity<String> handleWebhook(@RequestBody WebhookRequest webhook) {
        if ("DONE".equals(webhook.getStatus())) {
            // TODO: 주문 상태 업데이트, 결제 DB 반영 등
            System.out.println("결제 완료된 주문: {}"+ webhook.getOrderId());
        } else {
            System.out.println("️상태?: {}"+ webhook.getStatus());
        }
        return ResponseEntity.ok("Webhook processed");
    }
}
