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

    public void createAccount(Integer accountNr, BigInteger balance, BigInteger clientId){
        String sql = "INSERT INTO account (account_nr, balance, client_id) " +
                "VALUES (:account_nr, :balance, :client_id )";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_nr", accountNr);
        paramMap.put("balance", balance);
        paramMap.put("client_id", clientId);
        jdbcTemplate.update(sql, paramMap);
    }
    //Loob uue pangakonto

    public BigInteger getBalance(Integer accountNr) {
        String sql = "SELECT balance FROM account WHERE account_nr= :account_nr";   //Kontojäägi küsimine
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_nr", accountNr);
        BigInteger accountBal = jdbcTemplate.queryForObject(sql, paramMap, BigInteger.class);
        return accountBal;
    }
    //Annab kontojäägi

    public void updateBalanceMinus(Integer accountNr, BigInteger withdraw) {
        String sql2 = "UPDATE account SET balance = balance - :balance WHERE account_nr = :account_nr";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account_nr", accountNr);
        paramMap.put("balance", withdraw);
        jdbcTemplate.update(sql2, paramMap);
    }
    //Võtab kontolt raha maha

    public void updateBalancePlus(Integer accountNr2,
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

    public List<BankAccount> transactionHistory(Integer accountNr){
        String sql = "SELECT * FROM transaction_history " +
                "WHERE account_from_id: = account_from_id";   //Küsib tervet klientide listi
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_from_id", accountNr);
        return jdbcTemplate.query(sql, new HashMap<>(), new BankAccountRowMapper());
    }
    //Kuvab välja tehtud ülekannete, väljavõtete ja sissemaksete tabeli

    public void transferHistory (Integer accountToId, BigInteger deposit){
        String sql = "INSERT INTO transaction_history (account_to_id, amount) " +
                "VALUES (:account_to_id, :amount)";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_to_id", accountToId);
        paramMap.put("amount", deposit);
        jdbcTemplate.update(sql, paramMap);
    }
    //Lisab transaction_histroy tabelisse deposit kande

    public void transferHistory2 (Integer accountFromId, BigInteger withdraw){
        String sql = "INSERT INTO transaction_history (account_from_id, amount) " +
                "VALUES (:account_from_id, :amount)";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_from_id", accountFromId);
        paramMap.put("amount", withdraw);
        jdbcTemplate.update(sql, paramMap);
    }
    //lisab transaction_history tabelisse withdraw kande

    public void transferHistory3 (Integer accountFromId, Integer accountToId, BigInteger transfer){
        String sql = "INSERT INTO transaction_history (account_from_id, account_to_id, amount) " +
                "VALUES (:account_from_id, :account_to_id, :amount)";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_from_id", accountFromId);
        paramMap.put("account_to_id", accountToId);
        paramMap.put("amount", transfer);
        jdbcTemplate.update(sql, paramMap);
    }
    //liasb transaction_history tabelisse ülekande kontolt 1 kontole 2

    public Integer getUserId(Integer accountFromId){
        String sql = "SELECT id FROM account WHERE account_nr = :account_nr";
        //Võtab "id" tabelist "account" kohal "account_nr"
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_nr", accountFromId);
        Integer id = jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
        return id;
    }
    //Funktsioon kontonumbriga seotud unikaalse id saamiseks
}