package com.compassuol.sp.challenge.msfeedback.mapper;


import com.compassuol.sp.challenge.msfeedback.domain.dto.FeedbackRequest;
import com.compassuol.sp.challenge.msfeedback.domain.dto.FeedbackResponse;
import com.compassuol.sp.challenge.msfeedback.domain.entities.Feedback;

import java.util.ArrayList;
import java.util.List;

public class FeedbackMapper {

    public static Feedback toModel (FeedbackRequest request){

        var feedback = new Feedback()
                .builder()
                .scale(request.getScale())
                .comment(request.getComment())
                .orderId(request.getOrderId())
                .build();

        return feedback;
    }

    public static FeedbackResponse toDto (Feedback feedback){

        var response = new FeedbackResponse()
                .builder()
                .id(feedback.getId())
                .scale(feedback.getScale())
                .comment(feedback.getComment())
                .orderId(feedback.getOrderId())
                .build();

        return response;
    }

    public static List<FeedbackResponse> toList (List<Feedback> feedbackList){
        List<FeedbackResponse> feedbackResponseList = new ArrayList<>();
        for (Feedback feedback : feedbackList){
            feedbackResponseList.add(toDto(feedback));
        }

        return  feedbackResponseList;
    }

}
