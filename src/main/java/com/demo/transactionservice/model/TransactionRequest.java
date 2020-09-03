package com.demo.transactionservice.model;

import javax.validation.constraints.NotNull;

public class TransactionRequest {

    @NotNull(message = "amount must be set")
    private Long amount;
    @NotNull(message = "wallet phone number must be set")
    private String walletPhoneNumber;
    @NotNull(message = "merchant ID must be set")
    private Long merchantId;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getWalletPhoneNumber() {
        return walletPhoneNumber;
    }

    public void setWalletPhoneNumber(String walletPhoneNumber) {
        this.walletPhoneNumber = walletPhoneNumber;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }
}
