package com.compassuol.sp.challenge.msproducts.repositories;

import com.compassuol.sp.challenge.msproducts.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
