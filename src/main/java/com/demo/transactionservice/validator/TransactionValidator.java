package com.demo.transactionservice.validator;

import com.demo.transactionservice.common.ErrorMessage;
import com.demo.transactionservice.model.Merchant;
import com.demo.transactionservice.model.TransactionRequest;
import com.demo.transactionservice.model.Wallet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionValidator {

    public List<ErrorMessage> requestValidation(TransactionRequest request) {
        final List<ErrorMessage> errorMessages = new ArrayList<>();

        if (request.getWalletPhoneNumber().isEmpty() || request.getWalletPhoneNumber() == null) {
            errorMessages.add(new ErrorMessage("Wallet Phone Number", "Wallet phone number must be set"));
        }
        if (request.getMerchantId() == null ) {
            errorMessages.add(new ErrorMessage("Merchant Id", "Merchant ID must be set"));
        }
        if (request.getAmount() <= 0 || request.getAmount() == null) {
            errorMessages.add(new ErrorMessage("Amount", "Amount must be set and the value must be greather than 0"));
        }
        return errorMessages;
    }

    public List<ErrorMessage> transactionValidation(TransactionRequest request, Wallet wallet, Merchant merchant) {
        final List<ErrorMessage> errorMessages = new ArrayList<>();

        if (wallet == null) {
            errorMessages.add(new ErrorMessage("Wallet", "Wallet not found"));
        }
        if (merchant == null ) {
            errorMessages.add(new ErrorMessage("Merchant", "Merchant not found"));
        }
        if (wallet != null && wallet.getBalance() < request.getAmount()) {
            errorMessages.add(new ErrorMessage("Wallet balance", "Insufficient wallet balance"));
        }
        return errorMessages;
    }

}
