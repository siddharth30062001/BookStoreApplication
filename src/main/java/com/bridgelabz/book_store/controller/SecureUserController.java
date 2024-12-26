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


}
