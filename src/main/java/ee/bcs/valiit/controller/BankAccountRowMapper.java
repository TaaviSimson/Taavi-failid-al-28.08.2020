package ee.bcs.valiit.controller;

import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountRowMapper implements RowMapper<BankAccount> {

    @Override
    public BankAccount mapRow(ResultSet resultSet, int i) throws SQLException {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNr(resultSet.getString("account_nr"));
        //BigIntegert ei tunnista, võtame balance välja stringina, balance tule postgre-st (SQL)
        String balance = resultSet.getString("balance");
        //Defineerime muutuja BigInteger vormis väljastamiseks
        BigInteger bigIntegerBalance = new BigInteger(balance);
        //BanAccount mapis annab väärtusele AccountBal väärtuse bigIntegerBalace
        bankAccount.setAccountBal(bigIntegerBalance);
        return bankAccount;
    }
}