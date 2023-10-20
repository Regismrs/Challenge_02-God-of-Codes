package com.compassuol.sp.challenge.msfeedback.controllers;

import com.compassuol.sp.challenge.msfeedback.domain.dto.FeedbackRequest;
import com.compassuol.sp.challenge.msfeedback.domain.dto.FeedbackResponse;
import com.compassuol.sp.challenge.msfeedback.services.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public ResponseEntity<List
            <FeedbackResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(feedbackService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackResponse> updateFeedback(
            @PathVariable("id") Long id,
            @RequestBody FeedbackRequest fbRequest) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(feedbackService.updateFeedback(id, fbRequest));
    }

    @PostMapping
    public ResponseEntity<FeedbackResponse> createFeedback(@RequestBody FeedbackRequest feedbackRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(feedbackService.saveFeedback(feedbackRequest));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FeedbackResponse> getFeedback(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(feedbackService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFeedback(@PathVariable("id") Long id){
        feedbackService.deleteFeedback(id);
        return ResponseEntity.status(204).build();
    }

}
