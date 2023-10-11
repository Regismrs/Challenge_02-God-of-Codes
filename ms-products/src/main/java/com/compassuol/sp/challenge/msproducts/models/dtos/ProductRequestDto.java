package com.compassuol.sp.challenge.msproducts.models.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 10)
    private String description;

    @NotBlank
    @Positive
    private BigDecimal value;
}
