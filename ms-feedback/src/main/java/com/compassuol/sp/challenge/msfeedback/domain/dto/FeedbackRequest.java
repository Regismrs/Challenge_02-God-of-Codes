package com.compassuol.sp.challenge.msfeedback.domain.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackRequest {

    private Long orderId;

    private String scale;

    private String comment;

}
