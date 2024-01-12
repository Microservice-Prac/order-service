package com.example.orderservice.order.application.model;

import com.example.orderservice.order.domain.OrderEntity;
import lombok.*;

import java.util.UUID;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {
    private String productId;
    private Integer quantity;
    private Integer unitPrice;
    private String userId;

    public OrderEntity toEntity() {
        return OrderEntity.builder()
                .productId(productId)
                .quantity(quantity)
                .unitPrice(unitPrice)
                .totalPrice(calculateTotalPrice())
                .orderId(generateOrderId())
                .userId(userId)
                .build();
    }

    private Integer calculateTotalPrice() {
        if (quantity == null) {
            throw new IllegalArgumentException("Quantity must not be null");
        }
        if (unitPrice == null) {
            throw new IllegalArgumentException("Unit price must not be null");
        }

        return quantity * unitPrice;
    }

    private String generateOrderId() {
        return UUID.randomUUID().toString();
    }
}
