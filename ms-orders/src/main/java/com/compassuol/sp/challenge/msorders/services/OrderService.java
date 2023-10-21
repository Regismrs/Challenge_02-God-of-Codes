package com.compassuol.sp.challenge.msorders.services;

import com.compassuol.sp.challenge.msorders.domain.dto.*;
import com.compassuol.sp.challenge.msorders.domain.entities.Order;
import com.compassuol.sp.challenge.msorders.domain.entities.OrderProduct;
import com.compassuol.sp.challenge.msorders.enums.PaymentEnum;
import com.compassuol.sp.challenge.msorders.enums.StatusEnum;
import com.compassuol.sp.challenge.msorders.exceptions.NotFound;
import com.compassuol.sp.challenge.msorders.mapper.OrderMapper;
import com.compassuol.sp.challenge.msorders.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final AddressService addressService;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository,
                        ProductService productsService,
                        AddressService addressService) {

        this.orderRepository = orderRepository;
        this.addressService = addressService;
        this.productService = productsService;
    }

    public OrderResponse saveOrder(OrderRequest orderRequest) {

        Order order = new Order();

        order.setAddress(addressService
                .completeAddressWithAPI(orderRequest));

        order.setProducts(productService
                .completeProductsDataWithAPI(orderRequest));

        // total produto
        order.setPaymentMethod( orderRequest.getPaymentMethod() );
        order.setDiscount( calculateDiscountsPercentage(order) );
        order.setSubTotalValue( calculateProductTotalValue(order) );
        order.setTotalValue( calculateTotalWithDiscounts(order));
        order.setStatus(StatusEnum.CONFIRMED);

        //save
        Order saved = orderRepository.save(order);

        return OrderMapper.toDto(saved);
    }

    @Transactional
    public OrderResponse updateOrder(Long id, OrderRequest orderRequest) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFound("Not Found Order"));

        order.setAddress(addressService
                .completeAddressWithAPI(orderRequest));

        order.setProducts(productService
                .completeProductsDataWithAPI(orderRequest));

        // total produto
        order.setPaymentMethod( orderRequest.getPaymentMethod() );
        order.setDiscount( calculateDiscountsPercentage(order) );
        order.setSubTotalValue( calculateProductTotalValue(order) );
        order.setTotalValue( calculateTotalWithDiscounts(order));
        order.setStatus(StatusEnum.CONFIRMED);

        //save
        Order saved = orderRepository.save(order);

        return OrderMapper.toDto(saved);

    }

    private BigDecimal calculateTotalWithDiscounts(Order order) {
        return order.getSubTotalValue().multiply(
                        BigDecimal.ONE.subtract(order.getDiscount())
                );
    }

    private BigDecimal calculateProductTotalValue(Order order) {
        return order.getProducts().stream()
                .map(p -> p.getValue().multiply(BigDecimal.valueOf(p.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateDiscountsPercentage(Order order) {
        if (PaymentEnum.PIX == order.getPaymentMethod()) {
            return BigDecimal.valueOf(0.05);
        } else {
            return BigDecimal.ZERO;
        }
    }

}
