package com.compassuol.sp.challenge.msorders.domain.dto;

import com.compassuol.sp.challenge.msorders.domain.entities.OrderProduct;
import com.compassuol.sp.challenge.msorders.enums.PaymentEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderRequestDto {

    private List<OrderProduct> orderProducts;

    private AddressRequestDto addressRequestDto;

    private PaymentEnum paymentMethod;

}
