package com.compassuol.sp.challenge.msproducts.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    private Long Id;

    private String name;

    private String description;

    private BigDecimal value;
}
