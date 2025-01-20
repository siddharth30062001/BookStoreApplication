package com.bridgelabz.book_store.controller;


import com.bridgelabz.book_store.dto.*;
import com.bridgelabz.book_store.model.User;
import com.bridgelabz.book_store.service.BookService;
import com.bridgelabz.book_store.service.UserService;
import com.bridgelabz.book_store.serviceImpl.BookServiceImpl;
import com.bridgelabz.book_store.serviceImpl.UserServiceImpl;
import com.bridgelabz.book_store.util.TokenUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private TokenUtility tokenUtility;
    private BookService bookService;

    public UserController(UserService userService, TokenUtility tokenUtility, BookService bookService) {
        this.userService = userService;
        this.tokenUtility = tokenUtility;
        this.bookService = bookService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserRequestDTO userRequestDTO) {

        return new ResponseEntity<>(userService.registerUser(userRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO loginRequest){

        User login= userService.userLogin(loginRequest);

        if (login != null) {
            return ResponseEntity.ok(tokenUtility.createToken(login.getUserId(),login.getEmailId() ,login.getRole()));
        } else {
            return new ResponseEntity<>("User login not successfully", HttpStatus.OK);
        }
    }

    @GetMapping("/forgotpassword/{email}")
    public ResponseEntity<String> forgotPassword(@PathVariable String email) {
        return new ResponseEntity<>(userService.forgotPassword(email), HttpStatus.OK);
    }

    @GetMapping("/testChanges")
    public ResponseEntity<String> testChanges(){
        String message="Successfully Tested";
        return ResponseEntity.ok(message);
    }

}
