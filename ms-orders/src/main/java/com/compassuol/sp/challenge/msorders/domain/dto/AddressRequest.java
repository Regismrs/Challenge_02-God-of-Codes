package com.compassuol.sp.challenge.msorders.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class AddressRequest {

    private String street;

    @NotNull(message = "can't be null")
    @Pattern(regexp = "^[0-9]+$", message = "number should be numeric value or SN when not exists")
    private String number;

    @NotNull(message = "can't be null")
    @Pattern(regexp = "^[0-9]{8}|[0-9]{5}-[0-9]{3}$", message = "Postal code is invalid. Accept these patterns 00000000 or 00000-000")
    private String postalCode;

}
