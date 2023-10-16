package com.compassuol.sp.challenge.msorders.domain.dto;

import com.compassuol.sp.challenge.msorders.domain.entities.Product;
import com.compassuol.sp.challenge.msorders.enums.PaymentEnum;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {

    private Long id;

    private Integer quantity;

    private List<Product> products;

    private AddressResponseDto addressResponseDto;

    private PaymentEnum paymentMethod;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private LocalDateTime cancelDate;


}
