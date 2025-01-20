package com.bridgelabz.book_store.controller;


import com.bridgelabz.book_store.dto.CartResponseDTO;
import com.bridgelabz.book_store.service.CartService;
import com.bridgelabz.book_store.serviceImpl.CartServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    public CartController(CartService cartServiceImpl){
        this.cartService = cartService;
    }

    @DeleteMapping("/removeByCartId/{cartId}")
    public ResponseEntity<String> removeBookByCartId(@PathVariable Long cartId){
        String message= cartService.removeBookByCarId(cartId);
        return ResponseEntity.ok(message);

    }


    @GetMapping("/getAllCartItems")
    public ResponseEntity<List<CartResponseDTO>> getAllCartItems(){
        return new ResponseEntity<>(cartService.getAllCartItems(), HttpStatus.OK);
    }

}
