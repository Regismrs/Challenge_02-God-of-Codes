package com.compassuol.sp.challenge.msfeedback.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackResponse {

    private Long id;

    @JsonProperty("order_id")
    private Long orderId;
    //enum
    private String scale;

    private String comment;
}
