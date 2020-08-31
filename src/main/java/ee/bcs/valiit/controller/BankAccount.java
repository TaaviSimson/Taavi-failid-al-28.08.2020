package ee.bcs.valiit.controller;

import java.math.BigInteger;

//Alt + insert, seejärel vali Getter ja Setter, Ctrl + A, OK
public class BankAccount {
    private String accountNr;
    private BigInteger accountBal;

    public String getAccountNr() {
        return accountNr;
    }

    public void setAccountNr(String accountNr) {
        this.accountNr = accountNr;
    }

    public BigInteger getAccountBal() {
        return accountBal;
    }

    public void setAccountBal(BigInteger accountBal) {
        this.accountBal = accountBal;
    }
}


