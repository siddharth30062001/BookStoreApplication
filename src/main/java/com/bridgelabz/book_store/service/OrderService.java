package com.bridgelabz.book_store.service;


import com.bridgelabz.book_store.dto.OrderRequestDTO;
import com.bridgelabz.book_store.dto.OrderResponseDTO;
import com.bridgelabz.book_store.exception.CartNotFoundException;
import com.bridgelabz.book_store.exception.UserNotFoundException;
import com.bridgelabz.book_store.model.Cart;
import com.bridgelabz.book_store.model.Orders;
import com.bridgelabz.book_store.model.User;
import com.bridgelabz.book_store.repository.CartRepository;
import com.bridgelabz.book_store.repository.OrderRepository;
import com.bridgelabz.book_store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;


    public String placeOrder(Long userId, OrderRequestDTO orderRequestDTO,Long cartId) {

        User user= userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User Not Found"));
        //List<Cart> cart= cartRepository.findByUser(user);
        Cart cart= cartRepository.findById(cartId).orElseThrow(()-> new CartNotFoundException("Cart Not Found"));
        if(user.getUserId()!=cart.getUser().getUserId()){
            throw new CartNotFoundException("Not Found");
        }
        Orders order=new Orders();
        order.setAddress(orderRequestDTO.getAddress());
        order.setLocalDate(LocalDate.now());
        order.setUser(cart.getUser());
        order.setBookModel(cart.getBookModel());
        order.setPrice(cart.getTotalPrice());
        order.setQuantity(cart.getQuantity());
        order.setCancel(false);

        orderRepository.save(order);

        return "Order Placed Successfully";

    }

    public String cancelOrder(Long userId, Long orderId) {

        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if(userId != order.getUser().getUserId()){
            throw new RuntimeException("Order not cancelled");
        }
        order.setCancel(true);
        orderRepository.save(order);

        return "Order Cancel Successfully";
    }

    public List<OrderResponseDTO> getAllOrders(boolean cancel) {

        return orderRepository.findAllByCancel(cancel).stream().map((order)-> MapToDTO(order)).toList();
    }

    private OrderResponseDTO MapToDTO(Orders order) {

        OrderResponseDTO orderResponseDTO= new OrderResponseDTO();
        orderResponseDTO.setOrderId(order.getOrderId());
        orderResponseDTO.setAddress(order.getAddress());
        orderResponseDTO.setPrice(order.getPrice());
        orderResponseDTO.setQuantity(order.getQuantity());
        orderResponseDTO.setUser(order.getUser().getUserId());
        orderResponseDTO.setBookModel(order.getBookModel().getBookId());
        orderResponseDTO.setLocalDate(order.getLocalDate());
        orderResponseDTO.setCancel(false);

        return orderResponseDTO;

    }

    public List<OrderResponseDTO> getAllOrdersByUser(Long userId) {
        User user= userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User Not Found"));

        return orderRepository.findAllByUser_UserId(userId).stream().map((order)-> MapToDTO(order)).toList();
    }
}
