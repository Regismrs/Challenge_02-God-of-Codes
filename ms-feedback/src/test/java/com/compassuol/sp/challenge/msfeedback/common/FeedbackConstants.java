package com.compassuol.sp.challenge.msfeedback.common;

import com.compassuol.sp.challenge.msfeedback.domain.dto.FeedbackRequest;
import com.compassuol.sp.challenge.msfeedback.domain.dto.FeedbackResponse;
import com.compassuol.sp.challenge.msfeedback.domain.entities.Feedback;
import com.compassuol.sp.challenge.msfeedback.enums.ScaleEnum;

import java.util.Arrays;
import java.util.List;

public class FeedbackConstants {

    public static final Feedback INVALID_FEEDBACK = new Feedback();
    public static final Feedback FEEDBACK = new Feedback(1L, 1L, ScaleEnum.NEUTRAL, "abc");
    public static final FeedbackRequest FEEDBACK_REQUEST = new FeedbackRequest(1L, ScaleEnum.NEUTRAL, "abc");
    public static final FeedbackResponse FEEDBACK_RESPONSE = new FeedbackResponse(1L, 1L, ScaleEnum.NEUTRAL, "abc");

    public static final FeedbackResponse FEEDBACK_RES_DTO1 =
            new FeedbackResponse(
                    1L, 1L,  ScaleEnum.NEUTRAL, "abc");

    public static final FeedbackResponse FEEDBACK_RES_DTO2 =
            new FeedbackResponse(
                    2L, 2L,  ScaleEnum.NEUTRAL, "abc");


    public static final List<FeedbackResponse> FEEDBACK_LIST =
            Arrays.asList(FEEDBACK_RES_DTO1, FEEDBACK_RES_DTO2);

}

