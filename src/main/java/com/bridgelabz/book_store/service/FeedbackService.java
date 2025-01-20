package com.bridgelabz.book_store.service;

import com.bridgelabz.book_store.dto.FeedbackRequestDTO;
import com.bridgelabz.book_store.dto.FeedbackResponseDTO;

import java.util.List;

public interface FeedbackService {

    FeedbackResponseDTO addFeedback(Long userId, FeedbackRequestDTO feedbackDTO);

    List<FeedbackResponseDTO> getAllFeedbacks();
}
