package com.compassuol.sp.challenge.msorders.domain.dto;

import com.compassuol.sp.challenge.msorders.domain.entities.OrderProduct;
import com.compassuol.sp.challenge.msorders.enums.PaymentEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderRequestDto {

    @JsonProperty("products")
    private List<ProductRequestDto> orderProducts;

    @JsonProperty("address")
    private AddressRequestDto addressRequestDto;

    @JsonProperty("payment_method")
    private PaymentEnum paymentMethod;
}
