package com.rbittencourt.pa.orderreceiver.application.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbittencourt.pa.orderreceiver.infrastructure.order.Order;
import com.rbittencourt.pa.orderreceiver.infrastructure.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.HttpStatus.CREATED;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody OrderRequest request) throws JsonProcessingException {
        Order order = new Order(request.getClientId(), request.getPaymentPlan(), request.getProducts());
        sendNewOrderEvent(order);

        repository.save(order);

        return new ResponseEntity<>(CREATED);
    }

    private void sendNewOrderEvent(Order order) throws JsonProcessingException {
        String payload = objectMapper.writeValueAsString(order);
        kafkaTemplate.send("new_order", payload);
    }

}
