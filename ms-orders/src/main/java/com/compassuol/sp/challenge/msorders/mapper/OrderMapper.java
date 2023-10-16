package com.compassuol.sp.challenge.msorders.mapper;


import com.compassuol.sp.challenge.msorders.domain.dto.*;
import com.compassuol.sp.challenge.msorders.domain.entities.OrderProduct;
import com.compassuol.sp.challenge.msorders.domain.entities.Order;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderResponseDto orderResponseDto(Order order, AddressViaCepDto addressViaCepDto){
        //foreach de produto

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
                .addressResponseDto(addressResponseMapper)
                .build();
    }


    public OrderResponseDto orderList(Order order){
        var orderResponseDtoList = order.getProducts().stream()
                .map(this::orderResponseDto1).collect(Collectors.toList());
        return (OrderResponseDto) orderResponseDtoList;
    }

    private OrderResponseDto orderResponseDto1(OrderProduct productResponseDto){

        var orderResponse = new OrderResponseDto();

        orderResponse.setId(productResponseDto.getId());
        orderResponse.setQuantity(productResponseDto.getQuantity());
        return orderResponse;
    }

}
