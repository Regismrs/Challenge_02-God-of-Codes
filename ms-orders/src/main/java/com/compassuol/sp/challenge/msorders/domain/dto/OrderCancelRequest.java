package com.compassuol.sp.challenge.msorders.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class OrderCancelRequest {

    @NotEmpty(message = "can't be null")
    private String cancelReason;
}
