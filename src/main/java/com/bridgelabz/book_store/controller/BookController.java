package com.bridgelabz.book_store.controller;


import com.bridgelabz.book_store.dto.BookResponseDTO;
import com.bridgelabz.book_store.exception.UnauthorizedAccessException;
import com.bridgelabz.book_store.service.BookService;
import com.bridgelabz.book_store.serviceImpl.BookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/filter")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping("/addBook")
    public ResponseEntity<BookResponseDTO> addBook(@RequestAttribute("role") String role, @RequestParam String bookName, @RequestParam String author, @RequestParam String bookDescription, @RequestParam String logo, @RequestParam double price, @RequestParam int quantity) throws IOException {
        if(role.equals("User")){
            throw new UnauthorizedAccessException("Only ADMIN can add the book");
        }
        //String message= bookServiceImpl.addBook(bookName,author,bookDescription,logo,price,quantity);
        return new ResponseEntity<>(bookService.addBook(bookName,author,bookDescription,logo,price,quantity), HttpStatus.OK);
    }
}
