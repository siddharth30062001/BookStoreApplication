package com.bridgelabz.book_store.controller;

import com.bridgelabz.book_store.dto.FeedbackRequestDTO;
import com.bridgelabz.book_store.dto.FeedbackResponseDTO;
import com.bridgelabz.book_store.service.FeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/filter")
public class SecureFeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/addFeedback")
    public ResponseEntity<FeedbackResponseDTO> addFeedback(@RequestBody FeedbackRequestDTO feedbackRequestDTO, @RequestAttribute("userId") Long userId, @RequestAttribute("role") String role) {
        if("ADMIN".equalsIgnoreCase(role)) {
            throw new RuntimeException("Admin cant add feedback");
        }
        return new ResponseEntity<>(feedbackService.addFeedback(userId, feedbackRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/getFeedback")
    public ResponseEntity<List<FeedbackResponseDTO>> getAllFeedback() {
        return new ResponseEntity<>(feedbackService.getAllFeedbacks(), HttpStatus.OK);

    }
}
