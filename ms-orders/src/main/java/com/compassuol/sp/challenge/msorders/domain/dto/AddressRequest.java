package com.compassuol.sp.challenge.msorders.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {
    @NotBlank
    private String street;
    @NotEmpty
    private String number;
    @NotBlank
    private String postalCode;

}
