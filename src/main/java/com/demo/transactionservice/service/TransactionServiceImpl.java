package com.demo.transactionservice.service;

import com.demo.transactionservice.model.Merchant;
import com.demo.transactionservice.model.RecordsTransaction;
import com.demo.transactionservice.model.TransactionRequest;
import com.demo.transactionservice.model.Wallet;
import com.demo.transactionservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final String CREDIT = "CREDIT";
    private static final String DEBIT = "DEBIT";

    @Autowired
    private TransactionRepository repository;

    @Override
    public List<RecordsTransaction> findAll() {
        return repository.findAll();
    }

    @Override
    public RecordsTransaction saveTransaction(Wallet wallet) {
        RecordsTransaction records = new RecordsTransaction();
        records.setDate(LocalDateTime.now());
        records.setStatus(CREDIT);
        records.setAmount(wallet.getBalance());
        records.setWalletId(wallet.getId());
        records.setWalletName(wallet.getName());
        records.setWalletPhoneNumber(wallet.getPhoneNumber());
        return repository.save(records);
    }

    @Override
    public RecordsTransaction doPayment(TransactionRequest request, Wallet wallet, Merchant merchant) {
        RecordsTransaction record = new RecordsTransaction();
        record.setDate(LocalDateTime.now());
        record.setStatus(DEBIT);
        record.setWalletId(wallet.getId());
        record.setWalletName(wallet.getName());
        record.setWalletPhoneNumber(wallet.getPhoneNumber());
        record.setMerchantId(merchant.getId());
        record.setMerchantName(merchant.getName());
        record.setAmount(request.getAmount());
        return repository.save(record);
    }
}
