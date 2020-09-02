package com.demo.transactionservice.controller;

import com.demo.transactionservice.model.RecordsTransaction;
import com.demo.transactionservice.model.Wallet;
import com.demo.transactionservice.repository.TransactionRepository;
import com.demo.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService service;

    @Autowired
    private TransactionRepository repository;

    @PostMapping("/doTopUp")
    public RecordsTransaction doTopUp(@RequestBody Wallet wallet) {
        return service.saveTransaction(wallet);
    }

    @GetMapping("/findAllTransaction")
    public List<RecordsTransaction> findAllTransaction() {
        return repository.findAll();
    }
}
