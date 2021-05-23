package com.rbittencourt.pa.orderreceiver.infrastructure.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
