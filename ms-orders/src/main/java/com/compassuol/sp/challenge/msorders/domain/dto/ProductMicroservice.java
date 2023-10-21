package com.compassuol.sp.challenge.msorders.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductMicroservice {
    // dto para os produtos que vem do microservico products
    @JsonProperty("id")
    private Long productId;
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal value;
}