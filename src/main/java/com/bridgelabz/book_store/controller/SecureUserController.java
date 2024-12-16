package com.bridgelabz.book_store.controller;

import com.bridgelabz.book_store.dto.BookResponseDTO;
import com.bridgelabz.book_store.dto.UserRequestDTO;
import com.bridgelabz.book_store.dto.UserResponseDTO;
import com.bridgelabz.book_store.service.BookService;
import com.bridgelabz.book_store.service.UserService;
import com.bridgelabz.book_store.util.TokenUtility;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/filter")
public class SecureUserController {


    private TokenUtility tokenUtility;

    private UserService userService;

    private BookService bookService;

    public SecureUserController(UserService userService, TokenUtility tokenUtility, BookService bookService){
        this.userService= userService;
        this.tokenUtility= tokenUtility;
        this.bookService= bookService;
    }

    @GetMapping("/getUser")
    public UserResponseDTO getUserById(@RequestAttribute("userId") Long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestAttribute("userId") Long userId, @RequestBody UserRequestDTO userRequestDTO){
        String message= userService.updateUser(userId,userRequestDTO);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestAttribute("userId") Long userId){
        String message= userService.deleteUser(userId);
        return ResponseEntity.ok(message);
    }

//    @PutMapping("/updateBook/{bookId}")
//    public ResponseEntity<String> updateBook(@RequestAttribute("role") String role, @PathVariable Long bookId,@RequestParam String bookName, @RequestParam String author, @RequestParam String bookDescription, @RequestParam MultipartFile logo, @RequestParam double price, @RequestParam int quantity) throws IOException {
//        if(role.equals("User") || role== null){
//            throw new IllegalArgumentException("Only ADMIN can update  the book");
//        }
//        String message= bookService.updateBook(bookId,bookName,author,bookDescription,logo,price,quantity);
//        return ResponseEntity.ok(message);
//    }
//
//    @DeleteMapping("/deleteBook/{bookId}")
//    public ResponseEntity<String> deleteBook(@RequestAttribute("role") String role,@PathVariable Long bookId){
//        if(role.equals("User") || role== null){
//            throw new IllegalArgumentException("Only ADMIN can delete the book");
//        }
//        String message= bookService.deleteBook(bookId);
//        return ResponseEntity.ok(message);
//    }
//
//    @GetMapping("/getBook/{bookId}")
//    public BookResponseDTO getBookById(@PathVariable Long bookId){
//
//        return bookService.getBookById(bookId);
//    }
//
//    @PutMapping("/changeBookQuantity/{bookId}")
//    public ResponseEntity<String> changeBookQuantity(@RequestAttribute("role") String role,@PathVariable Long bookId, @RequestParam int quantity){
//        if(role.equals("User") || role== null){
//            throw new IllegalArgumentException("Only ADMIN can change quantity of  the book");
//        }
//
//        String message= bookService.changeBookQuantity(bookId,quantity);
//        return ResponseEntity.ok(message);
//    }
//
//    @PutMapping("/changeBookPrice/{bookId}")
//    public ResponseEntity<String> changeBookPrice(@RequestAttribute("role") String role,@PathVariable Long bookId, @RequestParam double price){
//        if(role.equals("User") || role== null){
//            throw new IllegalArgumentException("Only ADMIN can change quantity of  the book");
//        }
//
//        String message= bookService.changeBookPrice(bookId,price);
//        return ResponseEntity.ok(message);
//    }


}
