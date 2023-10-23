package com.compassuol.sp.challenge.msorders.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class ProductRequest {
    @Min(value = 1, message = "Should be a positive number.")
    @JsonProperty("product_id")
    private Long productId;

    @Min(value = 1, message = "Quantity min is equal 1")
    @JsonProperty("quantity")
    private Integer quantity;

}
