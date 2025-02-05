package com.bridgelabz.book_store.repository;

import com.bridgelabz.book_store.model.Book;
import com.bridgelabz.book_store.model.Cart;
import com.bridgelabz.book_store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    List<Cart> findByUser(User user);

}
