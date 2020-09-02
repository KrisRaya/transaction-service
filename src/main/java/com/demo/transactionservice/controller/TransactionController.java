package com.demo.transactionservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
