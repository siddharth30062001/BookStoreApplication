package com.bridgelabz.book_store.controller;

import com.bridgelabz.book_store.dto.UserRequestDTO;
import com.bridgelabz.book_store.dto.UserResponseDTO;
import com.bridgelabz.book_store.service.BookService;
import com.bridgelabz.book_store.service.UserService;
import com.bridgelabz.book_store.serviceImpl.BookServiceImpl;
import com.bridgelabz.book_store.serviceImpl.UserServiceImpl;
import com.bridgelabz.book_store.util.TokenUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filter")
public class SecureUserController {


    private TokenUtility tokenUtility;

    private UserService userService;

    private BookService bookService;

    public SecureUserController(UserService userService, TokenUtility tokenUtility, BookService bookService){
        this.userService= userService;
        this.tokenUtility= tokenUtility;
        this.bookService = bookService;
    }

    @GetMapping("/getUser")
    public ResponseEntity<UserResponseDTO> getUserById(@RequestAttribute("userId") Long userId) {

        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestAttribute("userId") Long userId, @RequestBody UserRequestDTO userRequestDTO){
         return new ResponseEntity<>(userService.updateUser(userId,userRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestAttribute("userId") Long userId){
        return new ResponseEntity<>(userService.deleteUser(userId),HttpStatus.ACCEPTED);
    }


}
