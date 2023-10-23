package com.compassuol.sp.challenge.msorders.common;

import com.compassuol.sp.challenge.msorders.domain.dto.AddressRequest;
import com.compassuol.sp.challenge.msorders.domain.dto.AddressResponse;
import com.compassuol.sp.challenge.msorders.domain.dto.ProductRequest;
import com.compassuol.sp.challenge.msorders.domain.dto.ProductResponse;
import com.compassuol.sp.challenge.msorders.domain.entities.Address;
import com.compassuol.sp.challenge.msorders.domain.entities.OrderProduct;
import com.compassuol.sp.challenge.msorders.enums.PaymentEnum;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderConstants {
    public static final List<OrderProduct> PRODUCTS = Arrays.asList(
            new OrderProduct(1L, 2, BigDecimal.TEN),
            new OrderProduct(2L, 5, BigDecimal.ONE)
    );

    public static final Address ADDRESS = new Address(
            "Street name",
            "10",
            "details",
            "City name",
            "State name",
            "31333333"
    );
    public static final List<ProductResponse> PRODUCTS_RESPONSE = Arrays.asList(
            new ProductResponse(1L, 2),
            new ProductResponse(2L, 5)
    );

    public static final AddressResponse ADDRESS_RESPONSE = new AddressResponse(
            "Street name",
            "10",
            "details",
            "City name",
            "State name",
            "31333333"
    );
}
