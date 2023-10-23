package com.compassuol.sp.challenge.msorders.controllers;


import com.compassuol.sp.challenge.msorders.domain.dto.OrderCancelRequest;
import com.compassuol.sp.challenge.msorders.domain.dto.OrderCancelResponse;
import com.compassuol.sp.challenge.msorders.domain.dto.OrderRequest;
import com.compassuol.sp.challenge.msorders.domain.dto.OrderResponse;
import com.compassuol.sp.challenge.msorders.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<OrderResponse> createOrder(
            @RequestBody OrderRequest orderDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.saveOrder(orderDto));
    }

    @PostMapping(path = "{id}/cancel")
    public ResponseEntity<OrderCancelResponse> cancelOrder(
            @PathVariable("id") Long id,
            @Valid @RequestBody OrderCancelRequest cancelReason) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.cancelOrder(id, cancelReason.getCancelReason()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(
            @PathVariable("id") Long id,
            @Valid @RequestBody OrderRequest orderDto) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.updateOrder(id, orderDto));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAll());
    }
  
}
