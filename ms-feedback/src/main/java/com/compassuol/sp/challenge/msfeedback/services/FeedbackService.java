package com.compassuol.sp.challenge.msfeedback.services;

import com.compassuol.sp.challenge.msfeedback.domain.dto.FeedbackResponse;
import com.compassuol.sp.challenge.msfeedback.domain.entities.Feedback;
import com.compassuol.sp.challenge.msfeedback.mapper.FeedbackMapper;
import com.compassuol.sp.challenge.msfeedback.repositories.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }
    public List<FeedbackResponse> getAll(){
        List<Feedback> feedbackList = feedbackRepository.findAll();

        return FeedbackMapper.toList(feedbackList);
    }

}
