package com.bridgelabz.book_store.repository;

import com.bridgelabz.book_store.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    Boolean existsByBookNameIgnoreCase(String bookName);

    Optional<Book> findByBookNameIgnoreCase(String BookName);
}
