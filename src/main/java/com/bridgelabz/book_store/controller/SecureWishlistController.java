package com.bridgelabz.book_store.controller;


import com.bridgelabz.book_store.dto.CartResponseDTO;
import com.bridgelabz.book_store.dto.WishlistResponseDTO;
import com.bridgelabz.book_store.exception.UnauthorizedAccessException;
import com.bridgelabz.book_store.service.WishlistService;
import com.bridgelabz.book_store.serviceImpl.WishlistServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filter")
public class SecureWishlistController {

    private WishlistService wishlistService;

    public SecureWishlistController(WishlistServiceImpl wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/addToWishlist")
    public ResponseEntity<WishlistResponseDTO> addToWishlist(@RequestAttribute("userId") Long userId, @RequestAttribute("role") String role, @RequestParam Long bookId) {
        if (role.equalsIgnoreCase("ADMIN")) {
            throw new UnauthorizedAccessException("Admin can't add to wish list");
        }

        WishlistResponseDTO wishlistResponseDTO= wishlistService.addToWishlist(userId,bookId);
        return new ResponseEntity<>(wishlistResponseDTO, HttpStatus.OK);
    }

    @GetMapping("getWishlist/{userId}")
    public ResponseEntity<List<WishlistResponseDTO>> addToWishlist(@RequestAttribute("userId") Long userId, @RequestAttribute("role") String role) {
        if (role.equalsIgnoreCase("ADMIN")) {
            throw new UnauthorizedAccessException("Admin can't get  wish list");
        }

        List<WishlistResponseDTO> wishlistResponseDTO= wishlistService.getUserWishList(userId);
        return new ResponseEntity<>(wishlistResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/removeFromWishlist/{bookId}")
    public ResponseEntity<String> removeFromWishlist(@RequestAttribute("role") String role,@RequestAttribute("userId") Long userId,@PathVariable Long bookId){

        if (role.equalsIgnoreCase("ADMIN")) {
            throw new UnauthorizedAccessException("Admin can't get  wish list");
        }

        String message = wishlistService.removeFromWishList(userId,bookId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/addToCart/{wishlistId}")
    public ResponseEntity<CartResponseDTO> addToCart(@RequestAttribute("role") String role,@RequestAttribute("userId") Long userId,@PathVariable Long wishlistId){
        if (role.equalsIgnoreCase("ADMIN")) {
            throw new UnauthorizedAccessException("Admin can't get  wish list");
        }

        CartResponseDTO cartResponseDTO = wishlistService.addToCart(userId,wishlistId);
        return new ResponseEntity<>(cartResponseDTO, HttpStatus.OK);
    }


}
