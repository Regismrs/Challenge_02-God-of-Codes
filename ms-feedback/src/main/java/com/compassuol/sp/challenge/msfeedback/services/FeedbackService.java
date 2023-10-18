package com.compassuol.sp.challenge.msfeedback.services;

import com.compassuol.sp.challenge.msfeedback.repositories.FeedbackRepository;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }
}
