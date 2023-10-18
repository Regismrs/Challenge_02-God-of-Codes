package com.compassuol.sp.challenge.msfeedback.domain.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackResponse {

    private Long id;

    private Long orderId;
    //enum
    private String scale;
    private String comment;
}
