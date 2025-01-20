package com.bridgelabz.book_store.service;

import com.bridgelabz.book_store.dto.OrderRequestDTO;
import com.bridgelabz.book_store.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {

    OrderResponseDTO placeOrder(Long userId, OrderRequestDTO orderRequestDTO, Long cartId);

    String cancelOrder(Long userId, Long orderId);

    List<OrderResponseDTO> getAllOrders(boolean cancel);

    List<OrderResponseDTO> getAllOrdersByUser(Long userId);
}
