package com.bridgelabz.book_store.service;

import com.bridgelabz.book_store.dto.CartResponseDTO;
import com.bridgelabz.book_store.dto.WishlistResponseDTO;

import java.util.List;

public interface WishlistService {

    WishlistResponseDTO addToWishlist(Long userId, Long bookId);

    List<WishlistResponseDTO> getUserWishList(Long userId);

    String removeFromWishList(Long userId, Long bookId);

    CartResponseDTO addToCart(Long userId, Long wishlistId);
}
