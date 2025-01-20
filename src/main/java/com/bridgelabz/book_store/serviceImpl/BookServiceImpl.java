package com.bridgelabz.book_store.serviceImpl;

import com.bridgelabz.book_store.dto.BookResponseDTO;
import com.bridgelabz.book_store.exception.BookNotFoundException;
import com.bridgelabz.book_store.model.Book;
import com.bridgelabz.book_store.repository.BookRepository;
import com.bridgelabz.book_store.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository= bookRepository;
    }

    @Override
    public BookResponseDTO addBook(String bookName,String author, String bookDescription,String logo,double price, int quantity) {

        boolean isBookAvailable = bookRepository.existsByBookNameIgnoreCase(bookName);
        if (isBookAvailable) {
            throw new BookNotFoundException("Book Already exists");
        }
        Book book = new Book();
        book.setBookName(bookName);
        book.setAuthor(author);
        book.setBookDescription(bookDescription);
        book.setLogo(logo);
        book.setPrice(price);
        book.setQuantity(quantity);
        return mapToDTO(bookRepository.save(book));

    }
        @Override
        public BookResponseDTO updateBook(Long bookId,String bookName,String author, String bookDescription,String logo,double price, int quantity) throws IOException {
            Book book= bookRepository.findById(bookId).orElseThrow(()->new BookNotFoundException("Book ID not Found"));
            book.setBookName(bookName);
            book.setAuthor(author);
            book.setBookDescription(bookDescription);
            book.setLogo(logo);
            book.setPrice(price);
            book.setQuantity(quantity);
            return mapToDTO(bookRepository.save(book));

        }
    @Override
    public String deleteBook(Long bookId) {
        Book book= bookRepository.findById(bookId).orElseThrow(()->new BookNotFoundException("Id not found"));
        bookRepository.deleteById(bookId);
        return "Book Deleted Successfully";
    }

    @Override
    public BookResponseDTO getBookById(Long id) {
        Book book= bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException("Book Not Found"));
        return mapToDTO(book);
    }

    private BookResponseDTO mapToDTO(Book book) {
        BookResponseDTO bookResponseDTO=new BookResponseDTO();
        bookResponseDTO.setBookId(book.getBookId());
        bookResponseDTO.setBookName(book.getBookName());
        bookResponseDTO.setBookDescription(book.getBookDescription());
        bookResponseDTO.setAuthor(book.getAuthor());
        bookResponseDTO.setPrice(book.getPrice());
        bookResponseDTO.setQuantity(book.getQuantity());
        bookResponseDTO.setLogo(book.getLogo());

        return bookResponseDTO;
    }

    @Override
    public String changeBookQuantity(Long bookId, int quantity) {

        Book book= bookRepository.findById(bookId).orElseThrow(()-> new BookNotFoundException("Book not Found"));
        book.setQuantity(quantity);
        bookRepository.save(book);
        return "Book quantity Changed";
    }

    @Override
    public String changeBookPrice(Long bookId, double price) {

        Book book= bookRepository.findById(bookId).orElseThrow(()-> new BookNotFoundException("Book not Found"));
        book.setPrice(price);
        bookRepository.save(book);
        return "Book Price Changed";
    }
}

