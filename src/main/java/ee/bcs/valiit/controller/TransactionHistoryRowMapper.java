package ee.bcs.valiit.controller;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionHistoryRowMapper implements RowMapper<TransactionHistory> {
//BankaccountRepository-s transfer_history juures

    @Override
    public TransactionHistory mapRow(ResultSet resultSet, int i) throws SQLException {
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setAccountFromId(resultSet.getString("account_from_id"));

        String accountToId = resultSet.getString("account_to_id");
        if (accountToId != null) {
            transactionHistory.setAccountToId(new BigInteger(accountToId));
        }
        //if tsükkel, et lubaks "null" lahtrit koodis

        String amount = resultSet.getString("amount");
        if (amount != null) {
            transactionHistory.setAmount(new BigInteger(amount));
        }
        return transactionHistory;
    }
}
//Rowmapper võtab tabelist read kohal account_from_id (=etteantud id), account_to_id ja amount ning annab
//selle põhjal ette ühe rea ja paneb Mapi sisse
