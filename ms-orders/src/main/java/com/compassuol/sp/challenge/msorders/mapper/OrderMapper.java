package com.compassuol.sp.challenge.msorders.mapper;


import com.compassuol.sp.challenge.msorders.domain.dto.*;
import com.compassuol.sp.challenge.msorders.domain.entities.Address;
import com.compassuol.sp.challenge.msorders.domain.entities.Order;
import com.compassuol.sp.challenge.msorders.domain.entities.OrderProduct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    public static OrderResponse toDto(Order order){

        return OrderResponse
                .builder()
                .id(order.getId())
                .products( orderProductsToProductsResponse(order.getProducts()) )
                .addressResponse( addressToDto(order.getAddress()) )
                .paymentMethod(order.getPaymentMethod())
                .subtotalValue(order.getSubTotalValue())
                .discount(order.getDiscount())
                .totalValue(order.getTotalValue())
                .createdDate(order.getCreatedDate())
                .status(order.getStatus())
                .build();
    }

    public static OrderCancelResponse toCancelDto(Order order){

        return OrderCancelResponse
                .builder()
                .id(order.getId())
                .products( orderProductsToProductsResponse(order.getProducts()) )
                .addressResponse( addressToDto(order.getAddress()) )
                .paymentMethod(order.getPaymentMethod())
                .subtotalValue(order.getSubTotalValue())
                .discount(order.getDiscount())
                .totalValue(order.getTotalValue())
                .createdDate(order.getCreatedDate())
                .status(order.getStatus())
                .cancelReason(order.getCancelReason())
                .cancelDate(order.getCancelDate())
                .build();
    }
    public static OrderProduct requestPlusProductMicroserviceToModel(
                ProductRequest request,
                ProductMicroservice productMicroservice) {

        OrderProduct product = new OrderProduct();
        product.setProductId(request.getProductId());
        product.setQuantity(request.getQuantity());
        product.setValue(productMicroservice.getValue());
        return product;
    }

    private static List<ProductResponse> orderProductsToProductsResponse(
            List<OrderProduct> orderProducts) {

        return orderProducts.stream().map(
                p -> new ProductResponse(p.getProductId(), p.getQuantity())
        ).toList();
    }

    private static AddressResponse addressToDto(Address address) {
        return AddressResponse
                .builder()
                .state(address.getState())
                .city(address.getCity())
                .number(address.getNumber())
                .complement(address.getComplement())
                .postalCode(address.getPostalCode())
                .street(address.getStreet())
                .build();
    }


    public static List<OrderResponse> toListDTO(List<Order> orderList){
        List<OrderResponse> orderResponseList = new ArrayList<>();
        for (Order order : orderList){
            orderResponseList.add(toDto(order));
        }
        return orderResponseList;
    }


}
