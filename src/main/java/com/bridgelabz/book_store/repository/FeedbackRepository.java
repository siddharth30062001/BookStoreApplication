package com.bridgelabz.book_store.repository;

import com.bridgelabz.book_store.model.Book;
import com.bridgelabz.book_store.model.Feedback;
import com.bridgelabz.book_store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Long> {

    Optional<Feedback> findByBookAndUser(Book book, User user);
}
