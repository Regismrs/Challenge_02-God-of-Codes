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

    @NotBlank(message = "field can't be blank")
    private String name;

    @NotBlank(message = "field can't be blank")
    @Size(min = 10, message = "field should be at least 10 chars")
    private String description;

    @NotNull(message = "field can't be null")
    @Positive(message = "field can't be negative number")
    private BigDecimal value;
}
