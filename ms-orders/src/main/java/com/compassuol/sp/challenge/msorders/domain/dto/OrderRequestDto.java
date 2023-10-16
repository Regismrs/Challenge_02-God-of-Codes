package com.compassuol.sp.challenge.msorders.domain.dto;

import com.compassuol.sp.challenge.msorders.domain.entities.Address;
import com.compassuol.sp.challenge.msorders.domain.entities.Product;
import com.compassuol.sp.challenge.msorders.enums.PaymentEnum;
import com.compassuol.sp.challenge.msorders.enums.StatusEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {


    private List<Product> products;

    private Address address;

    private String postalCode;

    private Integer number;

    private String complement;

    private PaymentEnum paymentMethod;

    private StatusEnum status;

    private BigDecimal subtotalValue;

    private BigDecimal discount;

    private BigDecimal totalValue;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private LocalDateTime cancelDate;
}
