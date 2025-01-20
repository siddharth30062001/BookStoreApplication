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
public class SecureBookController {

    private final BookService bookServiceImpl;

    public SecureBookController(BookServiceImpl bookServiceImpl){

        this.bookServiceImpl = bookServiceImpl;
    }

    @PutMapping("/updateBook/{bookId}")
    public ResponseEntity<BookResponseDTO> updateBook(@RequestAttribute("role") String role, @PathVariable Long bookId, @RequestParam String bookName, @RequestParam String author, @RequestParam String bookDescription, @RequestParam String logo, @RequestParam double price, @RequestParam int quantity) throws IOException {
        if(role.equalsIgnoreCase("User")){
            throw new UnauthorizedAccessException("Only ADMIN can update  the book");
        }
        BookResponseDTO book= bookServiceImpl.updateBook(bookId,bookName,author,bookDescription,logo,price,quantity);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("/deleteBook/{bookId}")
    public ResponseEntity<String> deleteBook(@RequestAttribute("role") String role,@PathVariable Long bookId){
        if(role.equalsIgnoreCase("User")){
            throw new UnauthorizedAccessException("Only ADMIN can delete the book");
        }
        String message= bookServiceImpl.deleteBook(bookId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping("/getBook/{bookId}")
    public  ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long bookId){

        return new ResponseEntity<>(bookServiceImpl.getBookById(bookId),HttpStatus.OK);
    }

    @PutMapping("/changeBookQuantity/{bookId}")
    public ResponseEntity<String> changeBookQuantity(@RequestAttribute("role") String role,@PathVariable Long bookId, @RequestParam int quantity){
        if(role.equalsIgnoreCase("User")){
            throw new UnauthorizedAccessException("Only ADMIN can change quantity of  the book");
        }

        String message= bookServiceImpl.changeBookQuantity(bookId,quantity);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/changeBookPrice/{bookId}")
    public ResponseEntity<String> changeBookPrice(@RequestAttribute("role") String role,@PathVariable Long bookId, @RequestParam double price){
        if(role.equalsIgnoreCase("User")){
            throw new UnauthorizedAccessException("Only ADMIN can change quantity of  the book");
        }

        String message= bookServiceImpl.changeBookPrice(bookId,price);
        return ResponseEntity.ok(message);
    }
}
