package com.bridgelabz.book_store.mapper;

import com.bridgelabz.book_store.dto.BookResponseDTO;
import com.bridgelabz.book_store.dto.UserResponseDTO;
import com.bridgelabz.book_store.model.Book;
import com.bridgelabz.book_store.model.User;

public class Mapper {
    public static BookResponseDTO mapBookToResponseDTO(Book book) {
        BookResponseDTO dto = new BookResponseDTO();
        dto.setBookId(book.getBookId());
        dto.setBookName(book.getBookName());
        dto.setAuthor(book.getAuthor());
        dto.setPrice(book.getPrice());
        dto.setQuantity(book.getQuantity());
        dto.setLogo(null);
        dto.setBookDescription(book.getBookDescription());
        return dto;
    }

    public static UserResponseDTO mapUserToResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUserId(user.getUserId());
        userResponseDTO.setFirstName(user.getFirstName());
        userResponseDTO.setLastName(user.getLastName());
        userResponseDTO.setEmailId(user.getEmailId());
        userResponseDTO.setDob(user.getDob());
        userResponseDTO.setRegisteredDate(user.getRegisteredDate());
        userResponseDTO.setRole(user.getRole());
        userResponseDTO.setUpdatedDate(user.getUpdatedDate());
        userResponseDTO.setPassword(user.getPassword());
        return userResponseDTO;
    }
}