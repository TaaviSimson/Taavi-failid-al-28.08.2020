package ee.bcs.valiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BankAccountRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
//Läheb sisse üks päring korraga

    public String bankBalance(String accountNr) {
        String sql = "SELECT balance FROM account WHERE account_nr= :account_nr";   //Küsi 1 element, nt balance
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_nr", accountNr);
        String vastus = jdbcTemplate.queryForObject(sql, paramMap, String.class);
        return vastus;
    }

    public BigInteger getBalance(String accountNr) {
        String sql = "SELECT balance FROM account WHERE account_nr= :account_nr";   //Kontojäägi küsimine
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_nr", accountNr);
        BigInteger accountBal = jdbcTemplate.queryForObject(sql, paramMap, BigInteger.class);
        return accountBal;
    }
    //Annab kontojäägi

    public void updateBalance(String accountNr, BigInteger withdraw) {
        String sql2 = "UPDATE account SET balance = balance - :balance WHERE account_nr = :account_nr";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account_nr", accountNr);
        paramMap.put("balance", withdraw);
        jdbcTemplate.update(sql2, paramMap);
    }
    //Võtab kontolt raha maha

    public void updateBalance2(String accountNr2,
                               BigInteger transfer) {
        String sql3 = "UPDATE account SET balance = balance + :balance WHERE account_nr = :account_nr";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_nr", accountNr2);
        paramMap.put("balance", transfer);
        jdbcTemplate.update(sql3, paramMap);
    }
    //Lisab kontole raha

    public List<BankAccount> clientlist(){
        String sql = "SELECT * FROM account";   //Küsi tervet klientide listi
        return jdbcTemplate.query(sql, new HashMap<>(), new BankAccountRowMapper());
    }
    //Kuvab välja kõikide klientide nimekirja (konto_nr ja kontoseis)
}
