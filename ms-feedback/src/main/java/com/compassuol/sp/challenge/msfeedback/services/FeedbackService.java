package com.compassuol.sp.challenge.msfeedback.services;

import com.compassuol.sp.challenge.msfeedback.domain.dto.FeedbackRequest;
import com.compassuol.sp.challenge.msfeedback.domain.dto.FeedbackResponse;
import com.compassuol.sp.challenge.msfeedback.domain.entities.Feedback;
import com.compassuol.sp.challenge.msfeedback.exceptions.NotFound;
import com.compassuol.sp.challenge.msfeedback.mapper.FeedbackMapper;
import com.compassuol.sp.challenge.msfeedback.repositories.FeedbackRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Transactional
    public FeedbackResponse updateProduct(Long id, FeedbackRequest fbRequest) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new NotFound("Feedback " + id + " not found."));

        feedback.setId(id);
        feedback.setComment(fbRequest.getComment());
        feedback.setScale(fbRequest.getComment());
        feedback.setOrderId(fbRequest.getOrderId());

        Feedback feedbackUpdated = feedbackRepository.save(feedback);

        return FeedbackMapper.toDto(feedbackUpdated);
    }
}
