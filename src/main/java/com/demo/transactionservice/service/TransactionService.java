package com.demo.transactionservice.service;

import com.demo.transactionservice.model.RecordsTransaction;
import com.demo.transactionservice.model.Wallet;

public interface TransactionService {

    public RecordsTransaction saveTransaction(Wallet wallet);
}
