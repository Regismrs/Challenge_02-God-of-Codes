package com.compassuol.sp.challenge.msproducts.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
@ToString
@Entity
@Table(name = "products_tb")
public class Product implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "field can't be blank")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "field can't be blank")
    @Size(min = 10, message = "field should be at least 10 chars")
    private String description;

    @Column(nullable = false)
    @NotNull(message = "field can't be null")
    @Positive(message = "field can't be negative number")
    private BigDecimal value;

    @CreationTimestamp
    @Column(name="create_date_time", updatable = false)
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name="update_date_time")
    private LocalDateTime updateDateTime;

    public Product() { }

    public Product(String name, String description, BigDecimal value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public Product(Long id, String name, String description, BigDecimal value) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.value = value;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Product product = (Product) o;
        return getId() != null && Objects.equals(getId(), product.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
