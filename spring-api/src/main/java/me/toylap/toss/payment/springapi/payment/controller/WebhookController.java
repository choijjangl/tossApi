package me.toylap.toss.payment.springapi.payment.controller;

import lombok.RequiredArgsConstructor;
import me.toylap.toss.payment.springapi.payment.entity.Order;
import me.toylap.toss.payment.springapi.payment.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


/*
* 주문 API
* */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class WebhookController {

    private final OrderRepository orderRepository;

    // 주문 생성 (결제 전 상태)
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody Order request) {

        Order order = Order.builder()
                .orderId(request.getOrderId())
                .orderName(request.getOrderName())
                .amount(request.getAmount())
                .status("READY")
                .requestedAt(LocalDateTime.now())
                .build();

        //주문 등록
        orderRepository.save(order);

        Map<String, Object> response = new HashMap<>();
        response.put("orderId", request.getOrderId());
        response.put("orderName", request.getOrderName());
        response.put("amount", request.getAmount());

        return ResponseEntity.ok(response);
    }

    // 주문 단건 조회 (프론트에서 결제 상태 확인용)
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderId) {
        return orderRepository.findByOrderId(orderId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
