package com.compassuol.sp.challenge.msorders.mapper;


import com.compassuol.sp.challenge.msorders.domain.dto.*;
import com.compassuol.sp.challenge.msorders.domain.entities.Address;
import com.compassuol.sp.challenge.msorders.domain.entities.OrderProduct;
import com.compassuol.sp.challenge.msorders.domain.entities.Order;
import org.springframework.stereotype.Component;

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

    // mapeia productRequest + productMicroservice = OrderProduct
    public static OrderProduct requestPlusProductMicroserviceToModel(
                ProductRequest request,
                ProductMicroservice productMicroservice) {

        OrderProduct product = new OrderProduct();
        product.setProductId(request.getProductId());
        product.setQuantity(request.getQuantity());
        product.setValue(productMicroservice.getValue());
        return product;
    }

    // mapeia os produtos da tabela para produtos da resposta
    private static List<ProductResponse> orderProductsToProductsResponse(
            List<OrderProduct> orderProducts) {

        return orderProducts.stream().map(
                p -> new ProductResponse(p.getProductId(), p.getQuantity())
        ).toList();
    }

    // mapeia do order para o endereco da resposta
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

}
