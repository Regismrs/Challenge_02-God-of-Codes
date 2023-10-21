package com.compassuol.sp.challenge.msorders.controllers;

import com.compassuol.sp.challenge.msorders.domain.dto.OrderRequest;
import com.compassuol.sp.challenge.msorders.domain.dto.OrderResponse;
import com.compassuol.sp.challenge.msorders.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest orderDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.saveOrder(orderDto));
    }

}
