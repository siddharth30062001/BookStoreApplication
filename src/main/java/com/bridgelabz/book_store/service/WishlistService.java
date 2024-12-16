package com.bridgelabz.book_store.service;

import com.bridgelabz.book_store.dto.BookResponseDTO;
import com.bridgelabz.book_store.dto.CartResponseDTO;
import com.bridgelabz.book_store.dto.WishlistRequestDTO;
import com.bridgelabz.book_store.dto.WishlistResponseDTO;
import com.bridgelabz.book_store.exception.BookNotFoundException;
import com.bridgelabz.book_store.exception.UserNotFoundException;
import com.bridgelabz.book_store.model.Book;
import com.bridgelabz.book_store.model.Cart;
import com.bridgelabz.book_store.model.User;
import com.bridgelabz.book_store.model.Wishlist;
import com.bridgelabz.book_store.repository.BookRepository;
import com.bridgelabz.book_store.repository.CartRepository;
import com.bridgelabz.book_store.repository.UserRepository;
import com.bridgelabz.book_store.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishlistService {

    private WishlistRepository wishlistRepository;
    private BookRepository bookRepository;
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    public WishlistService(WishlistRepository wishlistRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.wishlistRepository = wishlistRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }


    public WishlistResponseDTO addToWishlist(Long userId, Long bookId) {

        User user= userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User Not Found"));
        Book book= bookRepository.findById(bookId).orElseThrow(()-> new BookNotFoundException("Book Not Found"));
        if(wishlistRepository.existsByBook(book)) {
            throw new IllegalArgumentException("Book already exists in WishList");
        }
        Wishlist wishList = new Wishlist();
        wishList.setBook(book);
        wishList.setUser(user);
        wishList.setAvailable(book.getQuantity()>0);
        return mapToDTO(wishlistRepository.save(wishList));
    }

    public List<WishlistResponseDTO> getUserWishList(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<Wishlist> userWishList = wishlistRepository.findByUser(user);
        if(userWishList.isEmpty()) {
            throw new IllegalArgumentException("User does not have any wish list");
        }
        return userWishList.stream().map(wishList -> mapToDTO(wishList)).collect(Collectors.toList());
    }


    public String removeFromWishList(Long userId, Long bookId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        Wishlist userWishList = wishlistRepository.findByBookAndUser(book,user).orElseThrow(() -> new IllegalArgumentException("You don't have this book in wishlist"));
        wishlistRepository.delete(userWishList);
        return "Removed from WishList";
    }


    public CartResponseDTO addToCart(Long userId, Long wishlistId) {
        Wishlist wishList = wishlistRepository.findById(wishlistId).orElseThrow(() -> new IllegalArgumentException("WishList not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if(!wishlistRepository.existsByIdAndUser(wishlistId, user)) {
            throw new IllegalArgumentException("WishList does not exist");
        }
        Cart cart = new Cart();
        cart.setQuantity(1L);
        cart.setUser(user);
        cart.setBookModel(wishList.getBook());
        cart.setTotalPrice(wishList.getBook().getPrice());
        return mapCartToDTO(cartRepository.save(cart));
    }

    private CartResponseDTO mapCartToDTO(Cart cart) {
        CartResponseDTO cartResponseDTO = new CartResponseDTO();
        cartResponseDTO.setCartId(cart.getCartId());
        cartResponseDTO.setUserId(cart.getUser().getUserId());
        cartResponseDTO.setQuantity(cart.getQuantity());
        cartResponseDTO.setTotalPrice(cart.getTotalPrice());
        cartResponseDTO.setBookId(cart.getBookModel().getBookId());
        return cartResponseDTO;
    }

    private WishlistResponseDTO mapToDTO(Wishlist save) {
        WishlistResponseDTO wishList = new WishlistResponseDTO();
        wishList.setBook(mapBookToDTO(save.getBook()));
        wishList.setUser(save.getUser());
        wishList.setIsAvailable(save.getAvailable()?"Available":"Currently not available");
        return wishList;
    }

    private BookResponseDTO mapBookToDTO(Book book) {

        BookResponseDTO dto = new BookResponseDTO();
        dto.setBookId(book.getBookId());
        dto.setBookName(book.getBookName());
        dto.setAuthor(book.getAuthor());
        dto.setPrice(book.getPrice());
        dto.setQuantity(book.getQuantity());
        dto.setLogo(null);
        dto.setBookDescription(book.getBookDescription());
        return dto;
    }
}
