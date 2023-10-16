package com.compassuol.sp.challenge.msorders.domain.entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Product {

    private Long productId;

    private Integer productQuantity;

}
