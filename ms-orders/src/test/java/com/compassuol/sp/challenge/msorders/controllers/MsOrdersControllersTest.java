package com.compassuol.sp.challenge.msorders.controllers;

import com.compassuol.sp.challenge.msorders.domain.dto.OrderCancelRequest;
import com.compassuol.sp.challenge.msorders.domain.dto.OrderCancelResponse;
import com.compassuol.sp.challenge.msorders.enums.PaymentEnum;
import com.compassuol.sp.challenge.msorders.enums.StatusEnum;
import com.compassuol.sp.challenge.msorders.services.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.compassuol.sp.challenge.msorders.common.OrderConstants.ADDRESS_RESPONSE;
import static com.compassuol.sp.challenge.msorders.common.OrderConstants.PRODUCTS_RESPONSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MsOrdersControllersTest {
    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @Test
    public void testCancelOrderSuccess() {
        Long orderId = 1L;
        OrderCancelRequest cancelRequest = new OrderCancelRequest("Reason for cancellation");
        OrderCancelResponse expectedResponse = new OrderCancelResponse(1L,PRODUCTS_RESPONSE,ADDRESS_RESPONSE, PaymentEnum.CRYPTOCURRENCY, BigDecimal.ONE,BigDecimal.ZERO,BigDecimal.ONE, LocalDateTime.now(), StatusEnum.CANCELED,"Order successfully canceled",LocalDateTime.now());


        when(orderService.cancelOrder(orderId, cancelRequest.getCancelReason())).thenReturn(expectedResponse);

        ResponseEntity<OrderCancelResponse> response = orderController.cancelOrder(orderId, cancelRequest);

        verify(orderService).cancelOrder(orderId, cancelRequest.getCancelReason());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

}
