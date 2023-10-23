package com.compassuol.sp.challenge.msorders.domain.dto;

import com.compassuol.sp.challenge.msorders.enums.PaymentEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@Builder
public class OrderRequest {
    @Valid
    @JsonProperty("products")
    private List<ProductRequest> productsRequest;

    @Valid
    @JsonProperty("address")
    private AddressRequest addressRequest;

    @Enumerated(EnumType.STRING)
    @JsonProperty("payment_method")
    private PaymentEnum paymentMethod;
}
