package com.bridgelabz.book_store.controller;


import com.bridgelabz.book_store.dto.*;
import com.bridgelabz.book_store.model.User;
import com.bridgelabz.book_store.service.BookService;
import com.bridgelabz.book_store.service.UserService;
import com.bridgelabz.book_store.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {


    private UserService userService;
    private TokenUtility tokenUtility;

    private BookService bookService;

    public UserController(UserService userService, TokenUtility tokenUtility, BookService bookService) {
        this.userService = userService;
        this.tokenUtility = tokenUtility;
        this.bookService= bookService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody UserRequestDTO userRequestDTO) {
        String message = userService.registerUser(userRequestDTO);
        return ResponseEntity.ok(message);
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDTO loginRequest){

        User login= userService.userLogin(loginRequest);

        if (login != null) {
            return ResponseEntity.ok(tokenUtility.createToken(login.getUserId(),login.getEmailId() ,login.getRole()));
        } else {
            return new ResponseEntity<>("User login not successfully", HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/testChanges")
    public ResponseEntity<String> testChanges(){
        String message="Successfully Tested";
        return ResponseEntity.ok(message);
    }


}
