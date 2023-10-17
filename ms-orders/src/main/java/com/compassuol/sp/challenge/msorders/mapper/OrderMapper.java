package com.compassuol.sp.challenge.msorders.mapper;


import com.compassuol.sp.challenge.msorders.domain.dto.*;
import com.compassuol.sp.challenge.msorders.domain.entities.OrderProduct;
import com.compassuol.sp.challenge.msorders.domain.entities.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public static Order toModel(OrderRequestDto orderDto){

        List<OrderProduct> products =
                orderDto.getOrderProducts().stream()
                        .map(p -> new OrderProduct(p.getProductId(), p.getQuantity())).toList();

        Order order = new Order();
        order.setProducts(products);
        order.setNumber(orderDto.getAddressRequestDto().getNumber());
        order.setPostalCode(orderDto.getAddressRequestDto().getPostalCode());
        order.setPaymentMethod(orderDto.getPaymentMethod());
        return order;
    }

    //metodo funcionando igual ao ms-products
    public static OrderResponseDto toDto(Order order, AddressViaCepDto addressViaCepDto){

        List<ProductRequestDto> products =
                order.getProducts().stream()
                        .map(p -> new ProductRequestDto(p.getProductId(), p.getQuantity())).toList();

        var addressResponseMapper = new AddressResponseDto()
                .builder()
                .street(addressViaCepDto.logradouro())
                .number(order.getNumber())
                .complement(addressViaCepDto.bairro())
                .city(addressViaCepDto.localidade())
                .state(addressViaCepDto.uf())
                .postalCode(addressViaCepDto.cep())
                .build();

        return new OrderResponseDto()
                .builder()
                .id(order.getId())
                .products(products)
                .addressResponseDto(addressResponseMapper)
                .paymentMethod(order.getPaymentMethod())
                .subtotalValue(order.getSubTotalValue())
                .discount(order.getDiscount())
                .totalValue(order.getTotalValue())
                .createdDate(order.getCreatedDate())
                .status(order.getStatus())
                .build();
    }


    //rever
    public OrderResponseDto orderList(Order order){
        var orderResponseDtoList = order.getProducts().stream()
                .map(this::orderResponseDto1).collect(Collectors.toList());
        return (OrderResponseDto) orderResponseDtoList;
    }

    //rever
    private OrderResponseDto orderResponseDto1(OrderProduct productResponseDto){

        var orderResponse = new OrderResponseDto();

        orderResponse.setId(productResponseDto.getId());
        return orderResponse;
    }

    public static List<OrderResponseDto> toListDTO(List<Order> orderList, AddressViaCepDto addressViaCepDto){
        List<OrderResponseDto> orderResponseDTO = new ArrayList<>();
        for (Order order : orderList){
            orderResponseDTO.add(toDto(order, addressViaCepDto));
        }
        return orderResponseDTO;
    }

}
