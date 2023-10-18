package com.compassuol.sp.challenge.msfeedback.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.compassuol.sp.challenge.msfeedback.enums.ScaleEnum;
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

    private ScaleEnum scale;

    private String comment;
}
