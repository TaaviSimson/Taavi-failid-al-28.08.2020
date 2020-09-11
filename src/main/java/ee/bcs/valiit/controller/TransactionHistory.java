package ee.bcs.valiit.controller;

import java.math.BigInteger;

public class TransactionHistory {
    private String accountFromId;
    private BigInteger accountToId;
    private BigInteger amount;

    public String getAccountFromId() {
        return accountFromId;
    }

    public void setAccountFromId(String accountFromId) {
        this.accountFromId = accountFromId;
    }

    public BigInteger getAccountToId() {
        return accountToId;
    }

    public void setAccountToId(BigInteger accountToId) {
        this.accountToId = accountToId;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }
}
