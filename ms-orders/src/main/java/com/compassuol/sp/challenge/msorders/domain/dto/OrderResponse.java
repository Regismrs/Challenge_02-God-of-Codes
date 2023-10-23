package com.compassuol.sp.challenge.msorders.domain.dto;

import com.compassuol.sp.challenge.msorders.enums.PaymentEnum;
import com.compassuol.sp.challenge.msorders.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private Long id;

    private List<ProductResponse> products;

    @JsonProperty("address")
    private AddressResponse addressResponse;

    @JsonProperty("payment_method")
    private PaymentEnum paymentMethod;

    @JsonProperty("subtotal_value")
    private BigDecimal subtotalValue;

    private BigDecimal discount;

    @JsonProperty("total_value")
    private BigDecimal totalValue;

    private StatusEnum status;

    // formato 2023-07-20T12:00:00Z
    private LocalDateTime createdDate;
}
