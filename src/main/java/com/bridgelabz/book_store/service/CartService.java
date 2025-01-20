package com.bridgelabz.book_store.service;

import com.bridgelabz.book_store.dto.CartRequestDTO;
import com.bridgelabz.book_store.dto.CartResponseDTO;

import java.util.List;

public interface CartService {

    public CartResponseDTO addToCart(Long userId, CartRequestDTO cartRequestDTO);

    public String removeBookByCarId(Long cartId);

    public String removeByUserAndCartId(Long userId,Long cartId);

    public String updateQuantity(Long cartId, Long quantity);

    public List<CartResponseDTO> getAllCartItems();

    public List<CartResponseDTO> getCartItemsByUser(Long userId);
}
