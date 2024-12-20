package com.bridgelabz.book_store.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class FeedbackResponseDTO {

    private UserResponseDTO userResponseDTO;
    private BookResponseDTO bookResponseDTO;
    private String feedback;
    private Double rating;

    public UserResponseDTO getUserResponseDTO() {
        return userResponseDTO;
    }

    public void setUserResponseDTO(UserResponseDTO userResponseDTO) {
        this.userResponseDTO = userResponseDTO;
    }

    public BookResponseDTO getBookResponseDTO() {
        return bookResponseDTO;
    }

    public void setBookResponseDTO(BookResponseDTO bookResponseDTO) {
        this.bookResponseDTO = bookResponseDTO;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
