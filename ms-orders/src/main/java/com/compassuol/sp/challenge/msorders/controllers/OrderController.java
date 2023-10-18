package com.compassuol.sp.challenge.msorders.controllers;

import com.compassuol.sp.challenge.msorders.domain.dto.OrderRequestDto;
import com.compassuol.sp.challenge.msorders.domain.dto.OrderResponseDto;
import com.compassuol.sp.challenge.msorders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.saveOrder(orderDto));
    }

    //@PostMapping("/teste")
    //public ResponseEntity<Object> teste(@RequestBody OrderRequestDto orderDto) {
    //    return ResponseEntity.status(HttpStatus.OK)
    //            .body(orderDto);
    //}
}
