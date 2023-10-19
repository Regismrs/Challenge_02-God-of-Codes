package com.compassuol.sp.challenge.msorders.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @NotBlank
    @JsonProperty("product_id")
    private Long productId;
    @NotBlank
    @JsonProperty("quantity")
    private Integer quantity;

}
