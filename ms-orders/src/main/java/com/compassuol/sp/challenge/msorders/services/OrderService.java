package com.compassuol.sp.challenge.msorders.services;

import com.compassuol.sp.challenge.msorders.domain.dto.AddressViaCepDto;
import com.compassuol.sp.challenge.msorders.domain.dto.OrderRequestDto;
import com.compassuol.sp.challenge.msorders.domain.dto.OrderResponseDto;
import com.compassuol.sp.challenge.msorders.domain.entities.Order;
import com.compassuol.sp.challenge.msorders.enums.StatusEnum;
import com.compassuol.sp.challenge.msorders.mapper.OrderMapper;
import com.compassuol.sp.challenge.msorders.repositories.OrderRepository;
import com.compassuol.sp.challenge.msorders.services.third.ViaCepService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final ViaCepService viaCepService;

    @Autowired
    public OrderService(OrderRepository orderRepository, ViaCepService viaCepService) {
        this.orderRepository = orderRepository;
        this.viaCepService = viaCepService;
    }

    public List<OrderResponseDto> findAll(){
        List<Order> orders = orderRepository.findAll();

        return OrderMapper.toListDTO(orders, addressViaCepDto);
    }

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
