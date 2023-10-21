package com.compassuol.sp.challenge.msorders.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class OrderCancelRequest {

    @NotBlank
    private String cancelReason;
}
