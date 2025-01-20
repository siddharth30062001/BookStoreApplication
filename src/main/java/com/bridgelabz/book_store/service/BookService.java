package com.bridgelabz.book_store.service;

import com.bridgelabz.book_store.dto.BookResponseDTO;

import java.io.IOException;
import java.util.List;

public interface BookService {

    BookResponseDTO addBook(String bookName,String author, String bookDescription,String logo,double price, int quantity) throws IOException;

    BookResponseDTO updateBook(Long bookId,String bookName,String author, String bookDescription,String logo,double price, int quantity) throws IOException;

    String deleteBook(Long id);

    String changeBookQuantity(Long bookId, int quantity);

    String changeBookPrice(Long bookId, double price);

    //List<BookResponseDTO> getAllBooks();

    BookResponseDTO getBookById(Long id);
}
