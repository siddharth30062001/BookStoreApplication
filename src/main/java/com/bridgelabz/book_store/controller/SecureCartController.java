package com.bridgelabz.book_store.controller;

import com.bridgelabz.book_store.dto.CartRequestDTO;
import com.bridgelabz.book_store.dto.CartResponseDTO;
import com.bridgelabz.book_store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filter")
public class SecureCartController {

    @Autowired
    private CartService cartService;


    @PostMapping("/addToCart")
    public ResponseEntity<String> addToCart(@RequestAttribute("userId") Long userId, @RequestAttribute("role") String role, @RequestBody CartRequestDTO cartRequestDTO){

        if(role.equals("ADMIN")){
            throw new IllegalArgumentException("Only User can add the book");
        }

        String message= cartService.addToCart(userId,cartRequestDTO);
        return ResponseEntity.ok(message);

    }

    @DeleteMapping("/removeByUserAndCartId/{cartId}")
    public ResponseEntity<String> removeBookByUserId(@RequestAttribute("role") String role,@RequestAttribute("userId") Long userId,@PathVariable Long cartId){
        if(role.equals("ADMIN")){
            throw new IllegalArgumentException("Only User can delete the book");
        }
        String message= cartService.removeByUserAndCartId(userId,cartId);
        return ResponseEntity.ok(message);

    }

    @PutMapping("/updateQuantity/{cartId}/{quantity}")
    public ResponseEntity<String> updateQuantity(@RequestAttribute("role") String role,@PathVariable Long cartId, @PathVariable Long quantity){
        if(role.equals("ADMIN")){
            throw new IllegalArgumentException("Only User can delete the book");
        }
        String message= cartService.updateQuantity(cartId,quantity);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/getCartItemsByUser")
    public List<CartResponseDTO> getAllCartItems(@RequestAttribute("role") String role, @RequestAttribute("userId") Long userId){
        if(role.equals("ADMIN")){
            throw new IllegalArgumentException("Only User can delete the book");
        }
        return cartService.getCartItemsByUser(userId);
    }
}
