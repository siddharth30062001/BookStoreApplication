package com.bridgelabz.book_store.service;

import com.bridgelabz.book_store.dto.BookRequestDTO;
import com.bridgelabz.book_store.dto.BookResponseDTO;
import com.bridgelabz.book_store.dto.UserResponseDTO;
import com.bridgelabz.book_store.exception.BookNotFoundException;
import com.bridgelabz.book_store.exception.UserNotFoundException;
import com.bridgelabz.book_store.model.Book;
import com.bridgelabz.book_store.model.User;
import com.bridgelabz.book_store.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository= bookRepository;
    }

    public String addBook(String bookName,String author, String bookDescription,MultipartFile logo,double price, int quantity) throws IOException {

        boolean isBookAvailable = bookRepository.existsByBookNameIgnoreCase(bookName);
        if (isBookAvailable) {
            throw new BookNotFoundException("Book Already exists");
        }
        Book book = new Book();
        book.setBookName(bookName);
        book.setAuthor(author);
        book.setBookDescription(bookDescription);
        book.setLogo(logo.getBytes());
        book.setPrice(price);
        book.setQuantity(quantity);
        bookRepository.save(book);
        return "Book added Successfully";

    }
        public String updateBook(Long bookId,String bookName,String author, String bookDescription,MultipartFile logo,double price, int quantity) throws IOException {
            Book book= bookRepository.findById(bookId).orElseThrow(()->new BookNotFoundException("Book ID not Found"));
            book.setBookName(bookName);
            book.setAuthor(author);
            book.setBookDescription(bookDescription);
            book.setLogo(logo.getBytes());
            book.setPrice(price);
            book.setQuantity(quantity);
            bookRepository.save(book);
            return "Book updated Successfully";

        }

    public String deleteBook(Long bookId) {
        Book book= bookRepository.findById(bookId).orElseThrow(()->new BookNotFoundException("Id not found"));
        bookRepository.deleteById(bookId);
        return "Book Deleted Successfully";
    }

    public BookResponseDTO getBookById(Long id) {
        Book book= bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException("Book Not Found"));
        return mapToDTO(book);
    }

    private BookResponseDTO mapToDTO(Book book) {
        BookResponseDTO bookResponseDTO=new BookResponseDTO();
        bookResponseDTO.setBookName(book.getBookName());
        bookResponseDTO.setBookDescription(book.getBookDescription());
        bookResponseDTO.setAuthor(book.getAuthor());
        bookResponseDTO.setPrice(book.getPrice());
        bookResponseDTO.setQuantity(book.getQuantity());
        bookResponseDTO.setLogo(book.getLogo());

        return bookResponseDTO;
    }

    public String changeBookQuantity(Long bookId, int quantity) {

        Book book= bookRepository.findById(bookId).orElseThrow(()-> new BookNotFoundException("Book not Found"));
        book.setQuantity(quantity);
        bookRepository.save(book);
        return "Book quantity Changed";
    }

    public String changeBookPrice(Long bookId, double price) {

        Book book= bookRepository.findById(bookId).orElseThrow(()-> new BookNotFoundException("Book not Found"));
        book.setPrice(price);
        bookRepository.save(book);
        return "Book Price Changed";
    }
}

