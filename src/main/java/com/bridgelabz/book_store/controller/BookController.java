package com.bridgelabz.book_store.controller;


import com.bridgelabz.book_store.dto.BookRequestDTO;
import com.bridgelabz.book_store.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/filter")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService){
        this.bookService= bookService;
    }

    @PostMapping("/addBook")
    public ResponseEntity<String> addBook(@RequestAttribute("role") String role,@RequestParam String bookName, @RequestParam String author, @RequestParam String bookDescription, @RequestParam MultipartFile logo,@RequestParam double price,@RequestParam int quantity) throws IOException {
        if(role.equals("User")){
            throw new IllegalArgumentException("Only ADMIN can add the book");
        }
        String message= bookService.addBook(bookName,author,bookDescription,logo,price,quantity);
        return ResponseEntity.ok(message);
    }
}
