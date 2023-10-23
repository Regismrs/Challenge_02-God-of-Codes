package com.compassuol.sp.challenge.msorders.services;

import com.compassuol.sp.challenge.msorders.domain.dto.OrderCancelResponse;
import com.compassuol.sp.challenge.msorders.domain.dto.OrderRequest;
import com.compassuol.sp.challenge.msorders.domain.dto.OrderResponse;
import com.compassuol.sp.challenge.msorders.domain.entities.Order;
import com.compassuol.sp.challenge.msorders.domain.entities.OrderProduct;
import com.compassuol.sp.challenge.msorders.enums.PaymentEnum;
import com.compassuol.sp.challenge.msorders.enums.StatusEnum;
import com.compassuol.sp.challenge.msorders.exceptions.BusinessException;
import com.compassuol.sp.challenge.msorders.repositories.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.compassuol.sp.challenge.msorders.common.OrderConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MsOrdersServiceTest {

    @InjectMocks
    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;

    @Test
    public void testCancelOrderSuccess() {
        Order order = new Order();
        order.setProducts(PRODUCTS);
        order.setAddress(ADDRESS);
        order.setCreatedDate(LocalDateTime.now());
        order.setStatus(StatusEnum.CONFIRMED);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        OrderCancelResponse response = orderService.cancelOrder(1L, "Motivo de cancelamento válido");

        assertEquals(StatusEnum.CANCELED, order.getStatus());
        assertNotNull(order.getCancelDate());
        assertNotNull(response);
    }

    @Test
    public void testCancelAlreadyCanceledOrderThrowsException() {
        Order order = new Order();
        order.setProducts(PRODUCTS);
        order.setAddress(ADDRESS);
        order.setCreatedDate(LocalDateTime.now());
        order.setStatus(StatusEnum.CANCELED);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        try {
            orderService.cancelOrder(1L, "Motivo de cancelamento inválido");
            fail("Esperava uma BusinessException");
        } catch (BusinessException ex) {
            assertEquals("Order already canceled", ex.getMessage());
        }

    }
    @Test
    public void testCancelSentOrderThrowsException() {
        Order order = new Order();
        order.setProducts(PRODUCTS);
        order.setAddress(ADDRESS);
        order.setCreatedDate(LocalDateTime.now());
        order.setStatus(StatusEnum.SENT);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        try {
            orderService.cancelOrder(1L, "Motivo de cancelamento inválido");
            fail("Esperava uma BusinessException");
        } catch (BusinessException ex) {
            assertEquals("Can't cancel orders with status SENT", ex.getMessage());
        }
    }
    @Test
    public void testCancelOldOrder() {
        // Crie um pedido que é mais antigo do que 90 dias
        Order order = new Order();
        order.setProducts(PRODUCTS);
        order.setAddress(ADDRESS);
        order.setStatus(StatusEnum.CONFIRMED);
        order.setCreatedDate(LocalDateTime.now().minusDays(91));
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        // Tente chamar o serviço de cancelamento
        try {
            orderService.cancelOrder(1L, "Motivo de cancelamento inválido");
            fail("Esperava uma BusinessException");
        } catch (BusinessException ex) {
            // Verifique se a exceção foi lançada com a mensagem correta
            assertEquals("Can't cancel Orders older than 90 days from creation.", ex.getMessage());
        }
    }

    @Test
    void createOrderWithValidDataReturnProduct() {
        Order order = new Order();
        order.setProducts(PRODUCTS);
        order.setAddress(ADDRESS);
        order.setPaymentMethod(PaymentEnum.CREDIT_CARD);
    }

    @Test
    void getProductsReturnsEmptyList() {
        when(orderRepository.findAll()).thenReturn(Collections.emptyList());

        List<OrderResponse> sut = orderService.getAll();

        Assertions.assertThat(sut).isEmpty();
    }
}
