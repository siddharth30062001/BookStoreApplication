package com.bridgelabz.book_store.serviceImpl;

import com.bridgelabz.book_store.dto.FeedbackRequestDTO;
import com.bridgelabz.book_store.dto.FeedbackResponseDTO;
import com.bridgelabz.book_store.mapper.Mapper;
import com.bridgelabz.book_store.model.Book;
import com.bridgelabz.book_store.model.Feedback;
import com.bridgelabz.book_store.model.User;
import com.bridgelabz.book_store.repository.BookRepository;
import com.bridgelabz.book_store.repository.FeedbackRepository;
import com.bridgelabz.book_store.repository.UserRepository;
import com.bridgelabz.book_store.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FeedbackServiceImpl implements FeedbackService {


    @Autowired
    FeedbackRepository feedbackRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;


    @Override
    public FeedbackResponseDTO addFeedback(Long userId, FeedbackRequestDTO feedbackDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Book book = bookRepository.findById(feedbackDTO.getBookId()).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        Feedback feedback = feedbackRepository.findByBookAndUser(book, user).orElse(null);
        if(!Objects.isNull(feedback)) {
            throw new IllegalArgumentException("Feedback already exists");
        }
        feedback = new Feedback();
        feedback.setUser(user);
        feedback.setBook(book);
        feedback.setFeedback(feedbackDTO.getFeedback());
        if(feedbackDTO.getRating() < 0 || feedbackDTO.getRating()>5){
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        feedback.setRating(feedbackDTO.getRating());
        feedback = feedbackRepository.save(feedback);
        return mapFeedbackToDTO(feedback);
    }


    @Override
    public List<FeedbackResponseDTO> getAllFeedbacks() {
        List<Feedback> feedbackList = feedbackRepository.findAll();
        return feedbackList.stream().map(this::mapFeedbackToDTO).toList();
    }


    private FeedbackResponseDTO mapFeedbackToDTO(Feedback save) {
        FeedbackResponseDTO feedbackResponseDTO = new FeedbackResponseDTO();
        feedbackResponseDTO.setFeedback(save.getFeedback());
        feedbackResponseDTO.setRating(save.getRating());
        feedbackResponseDTO.setBookResponseDTO(Mapper.mapBookToResponseDTO(save.getBook()));
        feedbackResponseDTO.setUserResponseDTO(Mapper.mapUserToResponseDTO(save.getUser()));
        return feedbackResponseDTO;
    }
}
