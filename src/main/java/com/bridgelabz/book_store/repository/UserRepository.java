package com.bridgelabz.book_store.repository;


import com.bridgelabz.book_store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailIdAndPassword(String emailId, String role);

    Boolean existsByEmailId(String emailId);
}
