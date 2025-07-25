package me.toylap.toss.payment.springapi.payment.repository;

import me.toylap.toss.payment.springapi.payment.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<Order> findByOrderId(String orderId);
}
