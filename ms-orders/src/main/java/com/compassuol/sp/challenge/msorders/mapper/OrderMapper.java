package com.compassuol.sp.challenge.msorders.mapper;


import com.compassuol.sp.challenge.msorders.domain.dto.AddressResponseDto;
import com.compassuol.sp.challenge.msorders.domain.dto.OrderResponseDto;
import com.compassuol.sp.challenge.msorders.domain.entities.Product;
import com.compassuol.sp.challenge.msorders.domain.entities.Address;
import com.compassuol.sp.challenge.msorders.domain.entities.Order;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {

    Order order = new Order();

    OrderResponseDto orderResponseDto = new OrderResponseDto();


    public OrderResponseDto orderResponseDto(Order order){

        //foreach de produto

        var address = new Address();

        var addressMapper = new AddressResponseDto()
                .builder()
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .city(address.getCity())
                .state(address.getState())
                .postalCode(address.getPostalCode())
                .build();

        return new OrderResponseDto()
                .builder()
                .id(order.getId())
                .addressResponseDto(addressMapper)
                .build();
    }


    public OrderResponseDto orderList(Order order){
        var orderResponseDtoList = order.getProducts().stream()
                .map(this::orderResponseDto1).collect(Collectors.toList());
        return (OrderResponseDto) orderResponseDtoList;
    }

    private OrderResponseDto orderResponseDto1(Product productResponseDto){

        var orderResponse = new OrderResponseDto();

        orderResponse.setId(productResponseDto.getProductId());
        orderResponse.setQuantity(productResponseDto.getProductQuantity());
        return orderResponse;
    }

}
