package ee.bcs.valiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;


@RestController
public class BankController {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private final Map<String, BigInteger> allAccounts = new HashMap<>();
    //HashMap kustub peale iga serveri uuendust!

    @GetMapping("sqltest")      //Väljastab kontonumber kohal id = 1   TÖÖTAB
    public String testSql() {
        String sql = "SELECT account_nr From account where id = :id";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("id", 1);
        String vastus = jdbcTemplate.queryForObject(sql, paramMap, String.class);
        return vastus;
    }
    //localhost:8080/sqltest

    @PutMapping("sqltest2")     //Pangakonto seisu muutmine kohal id    TÖÖTAB
    public void sqltest2() {
        String sql = "UPDATE account SET balance = :balance where id = :id";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("balance", 33333);
        paramMap.put("id", 2);
        jdbcTemplate.update(sql, paramMap);
    }
    //localhost:8080/sqltest2

    @PostMapping("account")  //Uue pangakonto loomine TÖÖTAB
    public void account() {
        String sql = "INSERT INTO account (id, account_nr, balance, client_id) " +
                "VALUES (:id, :account_nr, :balance, :client_id )";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("id", 19);
        paramMap.put("account_nr", 876549);
        paramMap.put("balance", 56342);
        paramMap.put("client_id", 20);
        jdbcTemplate.update(sql, paramMap);
    }
    //localhost:8080/account

    @GetMapping("bankbalance/{accountNr}")      //Ühe kliendi pangajäägi vaatamine  TÖÖTAB
    public String getAccount(@PathVariable("accountNr") String accountNr) {
        String sql = "SELECT balance FROM account WHERE account_nr= :account_nr";   //Küsi 1 element, nt balance
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_nr", accountNr);
        String vastus = jdbcTemplate.queryForObject(sql, paramMap, String.class);
        return vastus;
    }
    //localhost:8080/bankbalance/123456


    @PutMapping("deposit/{accountNr}")   //Kontole raha lisamine    TÖÖTAB
    public void depositMoney(@PathVariable("accountNr") String accountNr,
                             @RequestParam("deposit") BigInteger deposit) {
        String sql = "UPDATE account SET balance = balance + :balance WHERE account_nr = :account_nr";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_nr", accountNr);
        paramMap.put("balance", deposit);
        jdbcTemplate.update(sql, paramMap);
    }
    //localhost:8080/deposit/555555?deposit=378





    /*
    @PostMapping("account")       //Uue pangakonto loomine  TÖÖTAB
    public void createAccount(@RequestBody BankAccount request) {
        //BankAccount on asukoht (Klass) ja request on muutuja
        //kuna @RequestBody, siis request.getAccount kontonumbri saamiseks
        //HashMap puhul put, mitte add!
        allAccounts.put(request.getAccountNr(), request.getAccountBal());
    }
    //localhost:8080/account


    @GetMapping("bankbalance/{accountNr}")     //Tagastab pangakonto seisu  TÖÖTAB
    public BigInteger getAccount(@PathVariable("accountNr") String accountNr) {
        return allAccounts.get(accountNr);  //Siin accountNr, sest Map, leiab ise vastava paari välja
    }
    //localhost:8080/bankbalance/333333


    @PutMapping("deposit/{accountNr}")     //Kannab loodud kontole raha     TÖÖTAB
    public void depositMoney(@PathVariable("accountNr") String accountNr,
                             @RequestParam("deposit") BigInteger deposit) {
        //Küsib sisendit juurdekantava summa kohta
        //URL real opereerides @RequestBody POLE VAJA
        BigInteger accountBal = allAccounts.get(accountNr);
        //Defineerime accountBal, allAcounts võtab info hashmapist kohal accountNr
        accountBal = accountBal.add(deposit);           //Lisab kontole summa "deposit"
        allAccounts.put(accountNr, accountBal);         //Kirjutab Mapis kontol oleva summa üle
    }
    //localhost:8080/deposit/123456?deposit=123 - kannab kontole 123 raha Postmani kaudu
    */

    @PutMapping("withdraw/{accountNr}")     //Võtab loodud kontolt raha     TÖÖTAB
    public String withdrawMoney(@PathVariable("accountNr") String accountNr,
                                @RequestParam("withdraw") BigInteger withdraw) {    //Küsib väljavõetavat summat
        BigInteger accountBal = allAccounts.get(accountNr);     //Kontojääk väärus Mapis kohal konto nr.
        if (accountBal.compareTo(withdraw) > 0) {               //Võrdleb kontoseisu väljavõetava summaga
            accountBal = accountBal.subtract(withdraw);         //Uus summa = kontojääk - väljavõte
            allAccounts.put(accountNr, accountBal);             //Pane uus väärtus Mapi õigele kohale
            return "Transfer successful";                       //Tagastab õnnestumise teate
        } else {
            allAccounts.put(accountNr, accountBal);             //Jätab alles esialgse kontoseisu
            return "You don`t have enough money";               //Tagastab ebaõnnestumise teate
        }
    }   //localhost:8080/withdraw/123456?withdraw=200


    @PutMapping("transfer/{accountNr}/{accountNr2}")     //Viib raha ühelt kontolt teisele  TÖÖTAB
    public String transferMoney(@PathVariable("accountNr") String accountNr,        //Esimene kontonumber
                                @PathVariable("accountNr2") String accountNr2,      //Teine kontonumber
                                @RequestParam("transfer") BigInteger transfer) {    //Ülekantav summa
        BigInteger accountBal = allAccounts.get(accountNr);     //Kontoseis esimesel kontol
        BigInteger accountBal2 = allAccounts.get(accountNr2);   //Kontoseis teisel kontol
        if (accountBal.compareTo(transfer) > 0) {               //Konto nr. 1 vs ülekantav summa
            accountBal = accountBal.subtract(transfer);         //Konto 1 - ülekantav summa
            accountBal2 = accountBal2.add(transfer);            //Konto 2 + ülekantav summa
            allAccounts.put(accountNr, accountBal);             //Sisendab uuendatud 1. konto jäägi
            allAccounts.put(accountNr2, accountBal2);           //Sisendab uuendatud 2. konto jäägi
            return "Transfer successful";                       //Tagastab "Ülekanne edukas"
        } else {
            allAccounts.put(accountNr, accountBal);             //Sisendab 1. konto muutmata jäägi
            allAccounts.put(accountNr2, accountBal2);           //Sisendab 2. konto muutmata jäägi
            return "Transfer failed. You don`t have enough money."; //Teatab ebaõnnestunud ülekandest
        }
    }   //localhost:8080/transfer/123456/654321?transfer=250
}