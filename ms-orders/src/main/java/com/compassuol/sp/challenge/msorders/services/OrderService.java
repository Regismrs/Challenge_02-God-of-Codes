package com.compassuol.sp.challenge.msorders.services;

import com.compassuol.sp.challenge.msorders.domain.dto.OrderCancelResponse;
import com.compassuol.sp.challenge.msorders.domain.dto.OrderRequest;
import com.compassuol.sp.challenge.msorders.domain.dto.OrderResponse;
import com.compassuol.sp.challenge.msorders.domain.entities.Order;
import com.compassuol.sp.challenge.msorders.domain.entities.OrderProduct;
import com.compassuol.sp.challenge.msorders.enums.PaymentEnum;
import com.compassuol.sp.challenge.msorders.enums.StatusEnum;
import com.compassuol.sp.challenge.msorders.exceptions.BusinessException;
import com.compassuol.sp.challenge.msorders.exceptions.NotFound;
import com.compassuol.sp.challenge.msorders.exceptions.NotFound;
import com.compassuol.sp.challenge.msorders.mapper.OrderMapper;
import com.compassuol.sp.challenge.msorders.repositories.OrderRepository;
import org.bouncycastle.oer.Switch;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
    @Transactional
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

    public OrderCancelResponse cancelOrder(Long id, String cancelReason)
        throws BusinessException {

        Order canceledOrder = orderRepository.findById(id)
                .orElseThrow(() -> new NotFound("Order " + id + " Not Found"));

        if (isOrderCancellable(canceledOrder).equals(Boolean.TRUE)) {
            // can cancel...
            canceledOrder.setCancelReason(cancelReason);
            canceledOrder.setCancelDate(LocalDateTime.now());
            canceledOrder.setStatus(StatusEnum.CANCELED);

            orderRepository.save(canceledOrder);
        }

        return OrderMapper.toCancelDto(canceledOrder);
    }

    private BigDecimal calculateTotalWithDiscounts(Order order) {
        return order.getSubTotalValue().multiply(
                        BigDecimal.ONE.subtract(order.getDiscount())
                ).setScale(2, RoundingMode.HALF_UP);
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

    private Boolean isOrderCancellable(Order order) throws BusinessException{
        // today - createdDay
        long daysDifference = ChronoUnit.DAYS.between(order.getCreatedDate(), LocalDateTime.now());
        if (daysDifference > 90) {
            throw new BusinessException("Can't cancel Orders older than 90 days from creation.");
        } else if (order.getStatus().equals(StatusEnum.SENT)) {
            throw new BusinessException("Can't cancel orders with status SENT");
        } else if (order.getStatus().equals(StatusEnum.CANCELED)) {
            throw new BusinessException("Order already canceled");
        }

        return true;
    }


}
