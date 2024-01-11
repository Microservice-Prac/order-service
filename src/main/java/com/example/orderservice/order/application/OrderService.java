package com.example.orderservice.order.application;

import com.example.orderservice.order.domain.entity.OrderEntity;
import com.example.orderservice.order.infrastructure.OrderRepository;
import com.example.orderservice.order.presentation.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void createOrder(OrderDto.CreateRequest orderDtoCreateRequest) {
        OrderEntity orderEntity = orderDtoCreateRequest.toEntity();
        orderRepository.save(orderEntity);
    }

    public OrderDto.Response getOrderById(String orderId) {
        return null;
    }

    public List<OrderDto.Response> getAllOrders() {
        return null;
    }
}
