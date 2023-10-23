package com.compassuol.sp.challenge.msorders.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_product_tb")
public class OrderProduct {

    //private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="product_id", nullable = false)
    private Long productId;

    //@JoinColumn(name = "order_id", referencedColumnName = "id")
    @ManyToOne
    private Order order;

    @Min(value = 1)
    private Integer quantity;

    private BigDecimal value;

    public OrderProduct(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public OrderProduct(Long productId, Integer quantity, BigDecimal value) {
        this.productId = productId;
        this.quantity = quantity;
        this.value = value;
    }
}