package me.toylap.toss.payment.springapi.payment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 64)
    private String orderId; // Toss 요청 시 사용하는 주문 ID

    @Column(nullable = false, length = 100)
    private String orderName; // 상품명 (예: 토스 티셔츠 외 2건)

    @Column(nullable = false)
    private Integer amount; // 결제 금액 (예: 50000)

    @Column(length = 200)
    private String paymentKey; // 승인된 결제 키 (결제 성공 후 저장)

    @Column(nullable = false)
    private String status; // READY, DONE, CANCELED 등

    private LocalDateTime requestedAt; // 결제 요청 시각
    private LocalDateTime approvedAt;  // 결제 승인 시각

    private String vldYn; // 삭제 여부
    
    @Column(updatable = false)
    private LocalDateTime regDt;
    private LocalDateTime modDt;
    private LocalDateTime delDt;

    @PrePersist
    public void onCreate() {
        this.regDt = LocalDateTime.now();
        this.modDt = this.regDt;
        this.vldYn = "Y";
    }

    @PreUpdate
    public void onUpdate() {
        this.modDt = LocalDateTime.now();
    }

    @PreRemove
    public void onDelete() {
        this.modDt = LocalDateTime.now();
        this.delDt = LocalDateTime.now();
        this.vldYn = "N";
    }
}
