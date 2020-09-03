package com.demo.transactionservice.service;

import com.demo.transactionservice.model.Merchant;
import com.demo.transactionservice.model.RecordsTransaction;
import com.demo.transactionservice.model.TransactionRequest;
import com.demo.transactionservice.model.Wallet;

import java.util.List;

public interface TransactionService {

    List<RecordsTransaction> findAll();

    RecordsTransaction saveTransaction(Wallet wallet);

    RecordsTransaction doPayment(TransactionRequest request, Wallet wallet, Merchant merchant);

}
