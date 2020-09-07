package ee.bcs.valiit.controller;

import java.math.BigInteger;

public class CreateAccountRequest {
    private Integer accountNr;
    private BigInteger balance;
    private BigInteger clientId;

    public Integer getAccountNr() {
        return accountNr;
    }

    public void setAccountNr(Integer accountNr) {
        this.accountNr = accountNr;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    public BigInteger getClientId() {
        return clientId;
    }

    public void setClientId(BigInteger clientId) {
        this.clientId = clientId;
    }
}
