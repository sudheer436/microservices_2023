package com.javatechie.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javatechie.common.TransactionRequest;
import com.javatechie.common.TransactionResponse;
import com.javatechie.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    /*@PostMapping("/bookOrder")
    public Order bookOrder(@RequestBody Order order) {
        return service.saveOrder(order);
    }*/

   /* POST: localhost:9192/order/bookOrder
    {
            "id":101,
            "name":"pushpa",
            "qty":1,
            "price":1000
    }*/

    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request) throws JsonProcessingException {
        return service.saveOrder(request);
    }

        /*
            POST: localhost:9192/order/bookOrder
            {
                "order":{
                        "id":103,
                        "name":"pushpa3",
                        "qty":1,
                        "price":1000
                 },
                "payment":{}
            }
        */
}