package com.demo.transactionservice.model;

public class TransactionRequest {

    private Long amount;
    private String walletPhoneNumber;
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
