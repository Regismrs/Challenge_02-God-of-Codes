package com.compassuol.sp.challenge.msorders.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_product_tb")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="product_id", nullable = false)
    private Long productId;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinTable(name = "orders_products_tb",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private Order order;

    @Min(value = 1)
    private Integer quantity;

    private BigDecimal value;

    public OrderProduct(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}