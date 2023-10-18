package com.compassuol.sp.challenge.msorders.domain.dto;

import com.compassuol.sp.challenge.msorders.enums.PaymentEnum;
import com.compassuol.sp.challenge.msorders.enums.StatusEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {

    private Long id;

    private List<ProductRequestDto> products;

    private AddressResponseDto addressResponseDto;

    //nome = payment_method
    private PaymentEnum paymentMethod;

    //nome = subtotal_value
    private BigDecimal subtotalValue;

    private BigDecimal discount;

    //nome = total_value
    private BigDecimal totalValue;

    private StatusEnum status;

    // formato 2023-07-20T12:00:00Z
    private LocalDateTime createdDate;
}
