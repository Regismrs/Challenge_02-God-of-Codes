package com.compassuol.sp.challenge.msfeedback.controllers;

import com.compassuol.sp.challenge.msfeedback.services.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<Object> createFeedback(Object fbRequestDto) {

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
