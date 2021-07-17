package com.rbittencourt.pa.orderreceiver.application.order;

import com.rbittencourt.pa.orderreceiver.infrastructure.ecommerceorder.EcommerceOrder;
import com.rbittencourt.pa.orderreceiver.infrastructure.ecommerceorder.EcommerceOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.HttpStatus.CREATED;

@Controller
public class EcommerceOrderController {

    @Autowired
    private EcommerceOrderRepository repository;

    @Autowired
    private KafkaTemplate<String, EcommerceOrder> kafkaTemplate;

    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody EcommerceOrderRequest request) {
        EcommerceOrder ecommerceOrder = new EcommerceOrder(request.getClientId(), request.getPaymentPlan(), request.getProducts());
        kafkaTemplate.send("new_ecommerce_order", ecommerceOrder);

        repository.save(ecommerceOrder);

        return new ResponseEntity<>(CREATED);
    }

}
