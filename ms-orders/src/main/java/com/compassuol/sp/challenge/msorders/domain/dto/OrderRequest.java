package com.compassuol.sp.challenge.msorders.domain.dto;

import com.compassuol.sp.challenge.msorders.enums.PaymentEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderRequest {
    @NotNull
    @JsonProperty("products")
    private List<ProductRequest> productsRequest;

    @NotNull
    @JsonProperty("address")
    private AddressRequest addressRequest;

    @JsonProperty("payment_method")
    private PaymentEnum paymentMethod;
}
