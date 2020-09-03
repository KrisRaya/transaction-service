package com.demo.transactionservice.model;

import javax.validation.constraints.NotNull;

public class WalletTransaction {

    @NotNull(message = "Phone number cannot be empty")
    private String walletPhoneNumber;
    @NotNull(message = "amount cannot be null or empty")
    private Long amount;

    public String getPhoneNumber() {
        return walletPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.walletPhoneNumber = phoneNumber;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}