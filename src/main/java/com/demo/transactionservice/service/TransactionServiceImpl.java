package com.demo.transactionservice.service;

import com.demo.transactionservice.model.RecordsTransaction;
import com.demo.transactionservice.model.Wallet;
import com.demo.transactionservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Override
    public RecordsTransaction saveTransaction(Wallet wallet) {
        RecordsTransaction records = new RecordsTransaction();
        records.setStatus("CREDIT");
        records.setAmount(wallet.getBalance());
        records.setWalletId(wallet.getId());
        records.setWalletName(wallet.getName());
        return repository.save(records);
    }
}
