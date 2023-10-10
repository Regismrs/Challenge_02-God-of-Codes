package com.compassuol.sp.challenge.msproducts.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;


@Entity
@Table(name = "products_tb")
public class Product implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, unique = true)
    @NotEmpty
    private String name;

    @Column(nullable = false)
    @Size(min = 10)
    @NotEmpty
    private String description;

    @Column(nullable = false)
    @NotNull
    @Positive
    private Long value;



}
