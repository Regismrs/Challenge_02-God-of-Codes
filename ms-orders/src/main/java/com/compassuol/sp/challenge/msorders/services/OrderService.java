package com.compassuol.sp.challenge.msorders.services;

import com.compassuol.sp.challenge.msorders.domain.dto.AddressViaCepDto;
import com.compassuol.sp.challenge.msorders.domain.dto.OrderRequestDto;
import com.compassuol.sp.challenge.msorders.domain.dto.OrderResponseDto;
import com.compassuol.sp.challenge.msorders.domain.entities.Order;
import com.compassuol.sp.challenge.msorders.enums.StatusEnum;
import com.compassuol.sp.challenge.msorders.mapper.OrderMapper;
import com.compassuol.sp.challenge.msorders.repositories.OrderRepository;
import com.compassuol.sp.challenge.msorders.services.third.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ViaCepService viaCepService;

    public OrderResponseDto saveOrder(OrderRequestDto orderDto) {
        Order order = OrderMapper.toModel(orderDto);
        /**
         * VALIDACOES
         */
        //cep existe?
        AddressViaCepDto viacepAddress = viaCepService.getAddressByPostalCode(order.getPostalCode());

        //sets
        order.setSubTotalValue(BigDecimal.valueOf(100.0));
        order.setDiscount(BigDecimal.valueOf(0.5));
        order.setTotalValue(order.getSubTotalValue().multiply(
                BigDecimal.ONE.subtract(order.getDiscount())));
        order.setStatus(StatusEnum.CONFIRMED);

        //save
        Order saved = orderRepository.save(order);

        return OrderMapper.toDto(saved, viacepAddress);
    }
}
