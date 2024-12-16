package com.bridgelabz.book_store.controller;


import com.bridgelabz.book_store.dto.OrderRequestDTO;
import com.bridgelabz.book_store.dto.OrderResponseDTO;
import com.bridgelabz.book_store.model.Orders;
import com.bridgelabz.book_store.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filter")
public class SecureOrderController {

    private OrderService orderService;

    public SecureOrderController(OrderService orderService){
        this.orderService= orderService;
    }

    @PostMapping("/placeOrder/{cartId}")
    public ResponseEntity<String> placeOrder(@RequestAttribute("role") String role, @RequestAttribute("userId") Long userId, @RequestBody OrderRequestDTO orderRequestDTO, @PathVariable Long cartId){

        if(role.equals("ADMIN")){
            throw new IllegalArgumentException("Only User can place Order");
        }

        String message= orderService.placeOrder(userId,orderRequestDTO,cartId);

        return ResponseEntity.ok(message);
    }


    @PutMapping("/cancelOrder/{orderId}")
    public ResponseEntity<String> cancelOrder(@RequestAttribute("role") String role,@RequestAttribute("userId") Long userId,@PathVariable Long orderId){

        if(role.equals("ADMIN")){
            throw new IllegalArgumentException("Only User can place Order");
        }

        String message= orderService.cancelOrder(userId,orderId);

        return ResponseEntity.ok(message);

    }


    @GetMapping("/getAllOrdersByUser")
    public List<OrderResponseDTO> getAllOrdersOfUser(@RequestAttribute("userId") Long userId){

           return orderService.getAllOrdersByUser(userId);

    }

}
