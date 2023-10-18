package com.compassuol.sp.challenge.msorders.repositories;

import com.compassuol.sp.challenge.msorders.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
