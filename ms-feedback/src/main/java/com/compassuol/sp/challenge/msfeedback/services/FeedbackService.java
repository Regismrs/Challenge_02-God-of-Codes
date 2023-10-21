package com.compassuol.sp.challenge.msfeedback.services;

import com.compassuol.sp.challenge.msfeedback.domain.dto.FeedbackRequest;
import com.compassuol.sp.challenge.msfeedback.domain.dto.FeedbackResponse;
import com.compassuol.sp.challenge.msfeedback.domain.entities.Feedback;
import com.compassuol.sp.challenge.msfeedback.exceptions.NotFound;
import com.compassuol.sp.challenge.msfeedback.mapper.FeedbackMapper;
import com.compassuol.sp.challenge.msfeedback.repositories.FeedbackRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public FeedbackResponse updateFeedback(Long id, FeedbackRequest fbRequest) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new NotFound("Feedback " + id + " not found."));

        feedback.setId(id);
        feedback.setComment(fbRequest.getComment());
        feedback.setScale(fbRequest.getScale());
        feedback.setOrderId(fbRequest.getOrderId());

        Feedback feedbackUpdated = feedbackRepository.save(feedback);

        return FeedbackMapper.toDto(feedbackUpdated);
    }

    @Transactional
    public FeedbackResponse saveFeedback(FeedbackRequest feedbackRequest) {
        Feedback feedback = FeedbackMapper.toModel(feedbackRequest);
        var feedbackSave = feedbackRepository.save(feedback);

        return FeedbackMapper.toDto(feedbackSave);
    }
    public FeedbackResponse findById(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new NotFound("Feedback not found"));
        return FeedbackMapper.toDto(feedback);
    }

    @Transactional
    public void deleteFeedback(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new NotFound("Feedback " + id +  " not found"));
        feedbackRepository.delete(feedback);
    }
}
