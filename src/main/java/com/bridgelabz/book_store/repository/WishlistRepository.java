package com.bridgelabz.book_store.repository;

import com.bridgelabz.book_store.model.Book;
import com.bridgelabz.book_store.model.User;
import com.bridgelabz.book_store.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,Long> {

    boolean existsByBook(Book book);

    List<Wishlist> findByUser(User user);

    Optional<Wishlist> findByBookAndUser(Book book, User user);

    boolean existsByIdAndUser(Long wishListId, User user);
}
