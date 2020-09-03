package com.demo.transactionservice.controller;

import com.demo.transactionservice.common.ErrorMessage;
import com.demo.transactionservice.common.ResponseWrapper;
import com.demo.transactionservice.model.*;
import com.demo.transactionservice.service.TransactionService;
import com.demo.transactionservice.validator.TransactionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RestController
public class TransactionController {

    private static final String STATUS = "status";

    @Autowired
    private TransactionService service;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TransactionValidator validator;

    @PostMapping("/doTopUp")
    public ResponseEntity<ResponseWrapper> doTopUp(@RequestBody Wallet wallet) {
        final RecordsTransaction recordsTransaction = service.saveTransaction(wallet);
        return ResponseEntity.ok(new ResponseWrapper(recordsTransaction, Collections.singletonMap(STATUS, HttpStatus.OK)));
    }

    @GetMapping("/findAllTransaction")
    public ResponseEntity<ResponseWrapper> findAllTransaction() {
        final List<RecordsTransaction> recordsTransactions = service.findAll();
        return ResponseEntity.ok(new ResponseWrapper(recordsTransactions, Collections.singletonMap(STATUS, HttpStatus.OK)));
    }

    @PostMapping("/doPayment")
    public ResponseEntity<ResponseWrapper> doPayment(@RequestBody TransactionRequest request) {
        final List<ErrorMessage> requestErrorMessages = validator.requestValidation(request);
        if (!requestErrorMessages.isEmpty()) {
            return new ResponseEntity(new ResponseWrapper(Collections.singletonMap(STATUS, HttpStatus.NOT_ACCEPTABLE),
                    requestErrorMessages), HttpStatus.NOT_ACCEPTABLE);
        }

        final Wallet wallet = restTemplate.getForObject(
                "http://wallet-service/findByPhoneNumber/" + request.getWalletPhoneNumber(), Wallet.class);

        final Merchant merchant = restTemplate.getForObject(
                "http://merchant-service/findById/" + request.getMerchantId(), Merchant.class);

        final List<ErrorMessage> transactionErrorMessages = validator.transactionValidation(request, wallet, merchant);
        if (!transactionErrorMessages.isEmpty()) {
            return new ResponseEntity(new ResponseWrapper(Collections.singletonMap(STATUS, HttpStatus.NOT_ACCEPTABLE),
                    transactionErrorMessages), HttpStatus.NOT_ACCEPTABLE);
        }

        final RecordsTransaction recordsTransaction = service.doPayment(request, wallet, merchant);

        WalletTransaction walletTransaction = new WalletTransaction();
        walletTransaction.setPhoneNumber(request.getWalletPhoneNumber());
        walletTransaction.setAmount(request.getAmount());

        restTemplate.postForEntity("http://wallet-service/deductBalance/", walletTransaction, Wallet.class);

        if (merchant != null) {
            merchant.setBalance(request.getAmount());
        }
        restTemplate.postForEntity("http://merchant-service/payMerchant/", merchant, Merchant.class);

        return ResponseEntity.ok(new ResponseWrapper(recordsTransaction, Collections.singletonMap(STATUS, HttpStatus.OK)));
    }
}
