package com.example.orderservice.order.presentation.dto;

import com.example.orderservice.core.util.ModelMapperUtil;
import com.example.orderservice.order.application.model.Order;
import com.example.orderservice.order.domain.OrderEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDto {

    @Setter
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CreateRequest {

        @NotBlank(message = "productId cannot be empty or null")
        private String productId;

        @Positive(message = "Quantity must be greater than or equal to 0")
        private Integer quantity;

        @NotNull(message = "unitPrice cannot be bull")
        private Integer unitPrice;

        public Order toModel(String userId) {
            return Order.builder()
                    .productId(productId)
                    .quantity(quantity)
                    .unitPrice(unitPrice)
                    .userId(userId)
                    .build();
        }
    }

    @Setter
    @Getter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {
        private String productId;
        private Integer quantity;
        private Integer unitPrice;
        private Integer totalPrice;
        private LocalDateTime createdAt;
        private String orderId;

        public static Response of(OrderEntity orderEntity) {
            return ModelMapperUtil.modelMapper().map(orderEntity, Response.class);
        }

        public static List<Response> of(List<OrderEntity> orderEntityList) {
            return orderEntityList.stream()
                    .map(Response::of)
                    .collect(Collectors.toList());
        }
    }
}
