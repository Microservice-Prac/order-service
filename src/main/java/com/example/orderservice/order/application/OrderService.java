package com.example.orderservice.order.application;

import com.example.orderservice.order.application.model.Order;
import com.example.orderservice.order.domain.OrderEntity;
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

    public void createOrder(Order order) {
        OrderEntity orderEntity = order.toEntity();
        orderRepository.save(orderEntity);
    }

    public OrderDto.Response getOrderById(String orderId) {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);

        return OrderDto.Response.of(orderEntity);
    }

    public List<OrderDto.Response> getAllOrdersByUserId(String userId) {
        List<OrderEntity> orderEntityList = orderRepository.findByUserId(userId);

        return OrderDto.Response.of(orderEntityList);
    }
}
