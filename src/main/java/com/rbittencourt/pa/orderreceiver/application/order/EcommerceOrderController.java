package com.rbittencourt.pa.orderreceiver.application.order;

import com.rbittencourt.pa.orderreceiver.infrastructure.ecommerceorder.EcommerceOrder;
import com.rbittencourt.pa.orderreceiver.infrastructure.ecommerceorder.EcommerceOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static java.lang.System.currentTimeMillis;
import static org.springframework.http.HttpStatus.CREATED;

@Controller
public class EcommerceOrderController {

    @Autowired
    private EcommerceOrderRepository repository;

    @Autowired
    private KafkaTemplate<String, EcommerceOrder> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(EcommerceOrderController.class);

    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody EcommerceOrderRequest request) {
        EcommerceOrder ecommerceOrder = new EcommerceOrder(request.getClientId(), request.getPaymentPlan(), request.getProducts());

        long initKafka = currentTimeMillis();
        kafkaTemplate.send("new_ecommerce_order", ecommerceOrder);
        long endKafka = currentTimeMillis();
        long timeKafka = endKafka - initKafka;

        long initDb = currentTimeMillis();
        repository.save(ecommerceOrder);
        long endDb = currentTimeMillis();
        long timeDb = endDb - initDb;

        logger.info("Kafka time: " + timeKafka + " Db time: " + timeDb + " Total time: " + (timeKafka + timeDb));

        return new ResponseEntity<>(CREATED);
    }

}
