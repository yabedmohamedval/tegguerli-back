package com.teg.teggerli_back.repository;

import com.teg.teggerli_back.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
