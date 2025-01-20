package com.bridgelabz.book_store.service;

import com.bridgelabz.book_store.dto.LoginRequestDTO;
import com.bridgelabz.book_store.dto.UserRequestDTO;
import com.bridgelabz.book_store.dto.UserResponseDTO;
import com.bridgelabz.book_store.model.User;
import org.springframework.stereotype.Service;


public interface UserService {

    UserResponseDTO registerUser(UserRequestDTO userRequestDTO);

    User userLogin(LoginRequestDTO loginRequestDTO);

    UserResponseDTO getUserById(Long id);

    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);

    String deleteUser(Long id);


    // UserResponseDTO resetPassword(ResetPasswordDTO resetPasswordDTO);

    String forgotPassword(String email);
}
