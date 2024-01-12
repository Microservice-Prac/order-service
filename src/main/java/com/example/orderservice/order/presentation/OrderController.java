package com.example.orderservice.order.presentation;

import com.example.orderservice.order.application.OrderService;
import com.example.orderservice.order.presentation.dto.OrderDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final Environment environment;
    private final OrderService orderService;

    @GetMapping("/health_check")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok(String.format("It's Working in Order Service on PORT %s", environment.getProperty("local.server.port")));
    }

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<Object> createOrder(@PathVariable String userId, @Valid @RequestBody OrderDto.CreateRequest orderDtoCreateRequest) {
        orderService.createOrder(orderDtoCreateRequest.toModel(userId));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<List<OrderDto.Response>> getAllOrdersByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(orderService.getAllOrdersByUserId(userId));
    }
}
