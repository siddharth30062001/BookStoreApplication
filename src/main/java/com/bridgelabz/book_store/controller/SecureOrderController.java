package com.bridgelabz.book_store.controller;


import com.bridgelabz.book_store.dto.OrderRequestDTO;
import com.bridgelabz.book_store.dto.OrderResponseDTO;
import com.bridgelabz.book_store.exception.UnauthorizedAccessException;
import com.bridgelabz.book_store.service.OrderService;
import com.bridgelabz.book_store.serviceImpl.OrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    public ResponseEntity<OrderResponseDTO> placeOrder(@RequestAttribute("role") String role, @RequestAttribute("userId") Long userId, @RequestBody OrderRequestDTO orderRequestDTO, @PathVariable Long cartId){

        if(role.equalsIgnoreCase("ADMIN")){
            throw new UnauthorizedAccessException("Only User can place Order");
        }

        OrderResponseDTO order= orderService.placeOrder(userId,orderRequestDTO,cartId);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    @PutMapping("/cancelOrder/{orderId}")
    public ResponseEntity<String> cancelOrder(@RequestAttribute("role") String role,@RequestAttribute("userId") Long userId,@PathVariable Long orderId){

        if(role.equalsIgnoreCase("ADMIN")){
            throw new UnauthorizedAccessException("Only User can place Order");
        }

        String message= orderService.cancelOrder(userId,orderId);

        return ResponseEntity.ok(message);

    }


    @GetMapping("/getAllOrdersByUser")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrdersOfUser(@RequestAttribute("userId") Long userId){

           return  new ResponseEntity<>(orderService.getAllOrdersByUser(userId),HttpStatus.OK);

    }

}
