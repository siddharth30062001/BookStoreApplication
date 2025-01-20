package com.bridgelabz.book_store.serviceImpl;

import com.bridgelabz.book_store.dto.CartRequestDTO;
import com.bridgelabz.book_store.dto.CartResponseDTO;
import com.bridgelabz.book_store.exception.BookNotFoundException;
import com.bridgelabz.book_store.exception.CartNotFoundException;
import com.bridgelabz.book_store.exception.UserNotFoundException;
import com.bridgelabz.book_store.model.Book;
import com.bridgelabz.book_store.model.Cart;
import com.bridgelabz.book_store.model.User;
import com.bridgelabz.book_store.repository.BookRepository;
import com.bridgelabz.book_store.repository.CartRepository;
import com.bridgelabz.book_store.repository.UserRepository;
import com.bridgelabz.book_store.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private BookRepository bookRepository;
    private UserRepository userRepository;

    public CartServiceImpl(CartRepository cartRepository, BookRepository bookRepository, UserRepository userRepository){
        this.cartRepository= cartRepository;
        this.bookRepository= bookRepository;
        this.userRepository= userRepository;

    }


    public CartResponseDTO addToCart(Long userId, CartRequestDTO cartRequestDTO) {
        System.out.println(userId);
        User user=userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found"));
        Book book= bookRepository.findById(cartRequestDTO.getBookModel()).orElseThrow(()-> new BookNotFoundException("Book Not found"));
        Cart cart= new Cart();
        cart.setUser(user);
        cart.setBookModel(book);
        cart.setQuantity(cartRequestDTO.getQuantity());
        cart.setTotalPrice( (cartRequestDTO.getQuantity())*(book.getPrice()));
        //cartRepository.save(cart);
        book.setQuantity(book.getQuantity()- cart.getQuantity());
        bookRepository.save(book);
        return MapToDTO(cartRepository.save(cart));
    }

    public String removeBookByCarId(Long cartId) {

        Cart cart= cartRepository.findById(cartId).orElseThrow(()-> new CartNotFoundException("Cart Not Found"));
        cartRepository.deleteById(cart.getCartId());

        return "Book Removed from cart";
    }

    public String removeByUserAndCartId(Long userId,Long cartId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        Cart cart= cartRepository.findById(cartId).orElseThrow(()-> new CartNotFoundException("Cart Not Found"));
        cartRepository.deleteById(cart.getCartId());
        return "Book Removed From Cart";

    }

    public String updateQuantity(Long cartId, Long quantity) {

        Cart cart= cartRepository.findById(cartId).orElseThrow(()-> new CartNotFoundException("Cart Not Found"));
        cart.setQuantity(quantity);
        cartRepository.save(cart);

        return "Quantity Updated Successfully";
    }

    public List<CartResponseDTO> getAllCartItems() {

        List<Cart> cartItems= cartRepository.findAll();

        return cartItems.stream().map(cartItem->MapToDTO(cartItem)).toList();
    }

    private CartResponseDTO MapToDTO(Cart cart) {

        CartResponseDTO cartResponseDTO=new CartResponseDTO();
        cartResponseDTO.setCartId(cart.getCartId());
        cartResponseDTO.setUserId(cart.getUser().getUserId());
        cartResponseDTO.setBookId(cart.getBookModel().getBookId());
        cartResponseDTO.setQuantity(cart.getQuantity());
        cartResponseDTO.setTotalPrice(cart.getTotalPrice());

        return cartResponseDTO;

    }

    public List<CartResponseDTO> getCartItemsByUser(Long userId) {

        User user= userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User Not Found"));
        List<Cart> cartItems= cartRepository.findByUser(user);
        return cartItems.stream().map((cartItem)-> MapToDTO(cartItem)).toList();
    }
}
