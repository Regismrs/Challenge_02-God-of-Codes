package com.compassuol.sp.challenge.msorders.domain.dto;

import com.compassuol.sp.challenge.msorders.enums.PaymentEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

    @JsonProperty("products")
    private List<ProductRequestDto> orderProducts;

    @JsonProperty("address")
    private AddressRequestDto addressRequestDto;

    @JsonProperty("payment_method")
    private PaymentEnum paymentMethod;
}
