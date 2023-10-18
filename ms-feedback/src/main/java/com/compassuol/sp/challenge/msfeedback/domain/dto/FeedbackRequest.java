package com.compassuol.sp.challenge.msfeedback.domain.dto;

import com.compassuol.sp.challenge.msfeedback.enums.ScaleEnum;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackRequest {

    private Long orderId;

    private ScaleEnum scale;

    private String comment;

}
