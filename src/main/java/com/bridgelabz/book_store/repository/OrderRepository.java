package com.bridgelabz.book_store.repository;



import com.bridgelabz.book_store.model.Orders;
import com.bridgelabz.book_store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Long> {

    List<Orders> findAllByCancel(boolean cancel);

     List<Orders> findAllByUser_UserId(Long userId);
}
