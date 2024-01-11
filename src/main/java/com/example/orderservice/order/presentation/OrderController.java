package com.example.orderservice.order.presentation;

import com.example.orderservice.order.application.OrderService;
import com.example.orderservice.order.presentation.dto.OrderDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody OrderDto.CreateRequest orderDtoCreateRequest) {
        orderService.createOrder(orderDtoCreateRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
