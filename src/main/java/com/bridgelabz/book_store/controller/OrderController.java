package com.bridgelabz.book_store.controller;

import com.bridgelabz.book_store.dto.OrderResponseDTO;
import com.bridgelabz.book_store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getAllOrders")
    public List<OrderResponseDTO> getAllOrders(@RequestParam boolean cancel){

        return orderService.getAllOrders(cancel);
    }

}
