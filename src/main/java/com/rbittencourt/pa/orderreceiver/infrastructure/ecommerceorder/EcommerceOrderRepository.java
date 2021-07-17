package com.rbittencourt.pa.orderreceiver.infrastructure.ecommerceorder;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EcommerceOrderRepository extends JpaRepository<EcommerceOrder, Long> {
}
