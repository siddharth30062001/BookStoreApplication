package com.bridgelabz.book_store.controller;

import com.bridgelabz.book_store.dto.CartRequestDTO;
import com.bridgelabz.book_store.dto.CartResponseDTO;
import com.bridgelabz.book_store.exception.UnauthorizedAccessException;
import com.bridgelabz.book_store.service.CartService;
import com.bridgelabz.book_store.serviceImpl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filter")
public class SecureCartController {

    @Autowired
    private CartService cartService;


    @PostMapping("/addToCart")
    public ResponseEntity<CartResponseDTO> addToCart(@RequestAttribute("userId") Long userId, @RequestAttribute("role") String role, @RequestBody CartRequestDTO cartRequestDTO){

        if(role.equalsIgnoreCase("ADMIN")){
            throw new UnauthorizedAccessException("Only User can add the book");
        }

        CartResponseDTO cart= cartService.addToCart(userId,cartRequestDTO);
        return new ResponseEntity<>(cart, HttpStatus.OK);

    }

    @DeleteMapping("/removeByUserAndCartId/{cartId}")
    public ResponseEntity<String> removeBookByUserId(@RequestAttribute("role") String role,@RequestAttribute("userId") Long userId,@PathVariable Long cartId){
        if(role.equalsIgnoreCase("ADMIN")){
            throw new UnauthorizedAccessException("Only User can delete the book");
        }
        String message= cartService.removeByUserAndCartId(userId,cartId);
        return ResponseEntity.ok(message);

    }

    @PutMapping("/updateQuantity/{cartId}/{quantity}")
    public ResponseEntity<String> updateQuantity(@RequestAttribute("role") String role,@PathVariable Long cartId, @PathVariable Long quantity){
        if(role.equalsIgnoreCase("ADMIN")){
            throw new UnauthorizedAccessException("Only User can update the book");
        }
        String message= cartService.updateQuantity(cartId,quantity);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/getCartItemsByUser")
    public ResponseEntity<List<CartResponseDTO>> getAllCartItems(@RequestAttribute("role") String role, @RequestAttribute("userId") Long userId){
        if(role.equalsIgnoreCase("ADMIN")){
            throw new UnauthorizedAccessException("Only User can delete the book");
        }
        return new ResponseEntity<>(cartService.getCartItemsByUser(userId),HttpStatus.OK);
    }
}
