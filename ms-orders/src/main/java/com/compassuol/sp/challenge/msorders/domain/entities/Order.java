package com.compassuol.sp.challenge.msorders.domain.entities;

import com.compassuol.sp.challenge.msorders.enums.PaymentEnum;
import com.compassuol.sp.challenge.msorders.enums.StatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "orders_products_tb",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<OrderProduct> products = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentEnum paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEnum status;

    @Column(nullable = false)
    @ColumnDefault("0.0")
    @NotNull
    private BigDecimal subTotalValue;

    @Column(nullable = false)
    @Min(value=0)
    @NotNull
    private BigDecimal discount;

    @Column(nullable = false)
    @Positive
    @NotNull
    private BigDecimal totalValue;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "address_street")),
            @AttributeOverride(name = "number", column = @Column(name = "address_number")),
            @AttributeOverride(name = "complement", column = @Column(name = "address_complement")),
            @AttributeOverride(name = "city", column = @Column(name = "address_city")),
            @AttributeOverride(name = "state", column = @Column(name = "address_state")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "address_postal_code"))
    })
    private Address address;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

    private LocalDateTime cancelDate;

    public void setProducts(List<OrderProduct> products) {
        // cuz update...
        this.products.clear();
        this.products.addAll(products);
    }
}
