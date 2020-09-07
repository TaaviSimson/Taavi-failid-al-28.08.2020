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

    public void createClient(String firstname, String lastname){
        String sql = "INSERT INTO clientlist (firstname, lastname) "+
                "VALUES (:firstname, :lastname)";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("firstname", firstname);
        paramMap.put("lastname", lastname);
        jdbcTemplate.update(sql, paramMap);
    }

    public void createAccount(String accountNr, BigInteger balance, BigInteger clientId){
        String sql = "INSERT INTO account (account_nr, balance, client_id) " +
                "VALUES (:account_nr, :balance, :client_id )";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_nr", accountNr);
        paramMap.put("balance", balance);
        paramMap.put("client_id", clientId);
        jdbcTemplate.update(sql, paramMap);
    }
    //Loob uue pangakonto

    public BigInteger getBalance(String accountNr) {
        String sql = "SELECT balance FROM account WHERE account_nr= :account_nr";   //Kontojäägi küsimine
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_nr", accountNr);
        BigInteger accountBal = jdbcTemplate.queryForObject(sql, paramMap, BigInteger.class);
        return accountBal;
    }
    //Annab kontojäägi

    public void updateBalanceMinus(String accountNr, BigInteger withdraw) {
        String sql2 = "UPDATE account SET balance = balance - :balance WHERE account_nr = :account_nr";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account_nr", accountNr);
        paramMap.put("balance", withdraw);
        jdbcTemplate.update(sql2, paramMap);
    }
    //Võtab kontolt raha maha

    public void updateBalancePlus(String accountNr2,
                                  BigInteger transfer) {
        String sql3 = "UPDATE account SET balance = balance + :balance WHERE account_nr = :account_nr";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_nr", accountNr2);
        paramMap.put("balance", transfer);
        jdbcTemplate.update(sql3, paramMap);
    }
    //Lisab kontole raha

    public List<BankAccount> clientlist(){
        String sql = "SELECT * FROM account";   //Küsib tervet klientide listi
        return jdbcTemplate.query(sql, new HashMap<>(), new BankAccountRowMapper());
    }
    //Kuvab välja kõikide klientide nimekirja (konto_nr ja kontoseis)


    public void transferHistory (String accountNr, BigInteger deposit){
        String sql = "INSERT INTO transaction_history (account_to, amount) " +
                "VALUES (:account_to, :amount)";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_to", accountNr);
        paramMap.put("amount", deposit);
        jdbcTemplate.update(sql, paramMap);
    }
    //Lisab transaction_histroy tabelisse deposit kande

    public void transferHistory2 (String accountNr, BigInteger withdraw){
        String sql = "INSERT INTO transaction_history (account_from, amount) " +
                "VALUES (:account_from, :amount)";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_from", accountNr);
        paramMap.put("amount", withdraw);
        jdbcTemplate.update(sql, paramMap);
    }
    //lisab transaction_history tabelisse withdraw kande

    public void transferHistory3 (String accountNr, String accountNr2, BigInteger transfer){
        String sql = "INSERT INTO transaction_history (account_from, account_to, amount) " +
                "VALUES (:account_from, :account_to, :amount)";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_from", accountNr);
        paramMap.put("account_to", accountNr2);
        paramMap.put("amount", transfer);
        jdbcTemplate.update(sql, paramMap);
    }
    //liasb transaction_history tabelisse ülekande kontolt 1 kontole 2
}