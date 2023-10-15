package com.compassuol.sp.challenge.msorders.domain.entities;

import com.compassuol.sp.challenge.msorders.enums.PaymentEnum;
import com.compassuol.sp.challenge.msorders.enums.StatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "order_tb")
public class Order implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name="order_id", referencedColumnName = "id")
    private List<OrderProduct> products;

    /**
     * @ManyToOne varios pedidos podem ser do mesmo cep
     * cascade = ao incluir/alterar o endereco salva na tabela address_tb
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="postalCode", referencedColumnName = "postalCode")
    private Address address;

    @Column(name="address_postal_code")
    private String postalCode;

    @Column(name="address_number")
    private Integer number;

    @Column(name="address_complement")
    private String complement;

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
