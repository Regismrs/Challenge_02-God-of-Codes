package com.compassuol.sp.challenge.msorders.domain.entities;

import com.compassuol.sp.challenge.msorders.enums.PaymentEnum;
import com.compassuol.sp.challenge.msorders.enums.StatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_tb")
public class Order implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private List<OrderProduct> products;

    private String number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotBlank
    private PaymentEnum paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotBlank
    private StatusEnum status;

    @Column(nullable = false)
    @Positive
    @NotNull
    private BigDecimal subtotalValue;

    @Column(nullable = false)
    @Positive
    @NotNull
    private BigDecimal discount;

    @Column(nullable = false)
    @Positive
    @NotNull
    private BigDecimal totalValue;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

    private LocalDateTime cancelDate;
}
