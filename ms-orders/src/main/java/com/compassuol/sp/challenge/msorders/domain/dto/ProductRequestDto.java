package com.compassuol.sp.challenge.msorders.domain.dto;

import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

    private Long productId;

    private Integer productQuantity;

}
