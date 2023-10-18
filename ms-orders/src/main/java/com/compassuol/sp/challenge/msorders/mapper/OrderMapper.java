package com.compassuol.sp.challenge.msorders.mapper;


import com.compassuol.sp.challenge.msorders.domain.dto.*;
import com.compassuol.sp.challenge.msorders.domain.entities.Order;
import com.compassuol.sp.challenge.msorders.domain.entities.OrderProduct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    public static Order toModel(OrderRequestDto orderDto){

        var address = new AddressResponseDto();

        List<OrderProduct> products =
                orderDto.getOrderProducts().stream()
                        .map(p -> new OrderProduct(p.getProductId(), p.getQuantity())).toList();

        Order order = new Order();
        order.setProducts(products);
        address.setNumber(orderDto.getAddressRequestDto().getNumber());
        address.setPostalCode(orderDto.getAddressRequestDto().getPostalCode());
        order.setPaymentMethod(orderDto.getPaymentMethod());
        return order;
    }

    public static OrderResponseDto toDto(Order request){

        ArrayList<ProductRequestDto> productRequest = new ArrayList<>();

        var addressResponseMapper = new AddressResponseDto()
                .builder()
                .street(request.getAddress().getStreet())
                .number(request.getAddress().getNumber())
                .postalCode(request.getAddress().getPostalCode())
                .build();

        return new OrderResponseDto()
                .builder()
                .products(productRequest)
                .addressResponseDto(addressResponseMapper)
                .paymentMethod(request.getPaymentMethod())
                .subtotalValue(request.getSubTotalValue())
                .discount(request.getDiscount())
                .totalValue(request.getTotalValue())
                .createdDate(request.getCreatedDate())
                .status(request.getStatus())
                .build();
    }
}
