package com.javatechie.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class RetryController {

    Logger logger = LoggerFactory.getLogger(RetryController.class);
    RestTemplate restTemplate= new RestTemplate();

    @GetMapping("/getInvoice")
    @Retry(name = "getInvoiceRetry", fallbackMethod = "getInvoiceFallback")
    public String getInvoice() {
        logger.info("getInvoice() call starts here");
        ResponseEntity<String> entity= restTemplate.getForEntity("http://localhost:8088/invoice/rest/find/2", String.class);
        // ResponseEntity<String> entity= restTemplate.getForEntity("http://localhost:8088/getMessage", String.class);
        logger.info("Response :" + entity.getStatusCode());
        return entity.getBody();
    }

    public String getInvoiceFallback(Exception e) {
        logger.info("---RESPONSE FROM FALLBACK METHOD---");
        return "SERVICE IS DOWN, PLEASE TRY AFTER SOMETIME !!!";
    }
}