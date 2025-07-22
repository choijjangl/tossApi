package me.toylap.toss.payment.springapi.payment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.toylap.toss.payment.springapi.payment.dto.PaymentConfirmRequest;
import me.toylap.toss.payment.springapi.payment.dto.TossPaymentResponse;
import me.toylap.toss.payment.springapi.payment.entity.Order;
import me.toylap.toss.payment.springapi.payment.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${toss.secret-key}")
    private String secretKey;

    public TossPaymentResponse confirmPayment(PaymentConfirmRequest request) {
        String url = "https://api.tosspayments.com/v1/payments/confirm";

        System.out.println("secretKey"+secretKey);
        System.out.println("request"+request);
        // 1. 인증 헤더 생성
        HttpHeaders headers = new HttpHeaders();

        // 시크릿 키값, JSON 타입
        headers.setBasicAuth(secretKey, "");
        headers.setContentType(MediaType.APPLICATION_JSON);

        System.out.println("paymentKey: " + request.getPaymentKey());
        System.out.println("orderId: " + request.getOrderId());
        System.out.println("amount: " + request.getAmount());

        // 2. 요청 바디 생성
        HttpEntity<PaymentConfirmRequest> entity = new HttpEntity<>(request, headers);

        // 3. 외부 API 요청
        ResponseEntity<TossPaymentResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                TossPaymentResponse.class
        );

        // 4. DB 상태 업데이트
        Order order = orderRepository.findByOrderId(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("주문이 없습니다."));

        order.setStatus("DONE");
        order.setApprovedAt(LocalDateTime.parse(response.getBody().getApprovedAt().substring(0, 19)));
        orderRepository.save(order);

        return response.getBody();
    }
}
