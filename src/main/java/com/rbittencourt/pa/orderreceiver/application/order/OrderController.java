package com.rbittencourt.pa.orderreceiver.application.order;

import com.rbittencourt.pa.orderreceiver.infrastructure.order.Order;
import com.rbittencourt.pa.orderreceiver.infrastructure.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.HttpStatus.CREATED;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository repository;

    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody OrderRequest request) {
        Order order = new Order(request.getClientId(), request.getPaymentPlan(), request.getProducts());

        repository.save(order);

        return new ResponseEntity<>(CREATED);
    }

}
