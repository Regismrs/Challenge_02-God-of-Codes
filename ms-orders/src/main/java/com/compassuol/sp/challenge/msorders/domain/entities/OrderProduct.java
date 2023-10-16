package com.compassuol.sp.challenge.msorders.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_product_tb")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long order_id;

    @Column(nullable = false)
    private Long product_id;

    @Min(value = 1)
    private Integer quantity;
}
