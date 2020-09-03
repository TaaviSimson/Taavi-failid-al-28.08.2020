package ee.bcs.valiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class BankController {
    //Kriipsutades RestControlleri välja koodi ei vaata

    @Autowired
    private BankAccountService bankAccountService;
    //Vajalik BankAccountService klassiga ühenduse loomiseks

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
        bankAccountService.depositMoney(accountNr, deposit);
    }
    //localhost:8080/deposit/555555?deposit=378


    @PutMapping("withdraw/{accountNr}")     //Kontolt raha maha võtmine     TÖÖTAB
    public String withdrawMoney(@PathVariable("accountNr") String accountNr,
                                @RequestParam("withdraw") BigInteger withdraw) {
        return bankAccountService.withdrawMoney(accountNr, withdraw);
    }
    //localhost:8080/withdraw/999999?withdraw=243   TÄIENDATUD
    //Koodijupid Repository ja Service klassides


    @PutMapping("transfer/{accountNr}/{accountNr2}")    //Ühelt kontolt teisele raha kandmine
    public String transferMoney(@PathVariable("accountNr") String accountNr,
                                @PathVariable("accountNr2") String accountNr2,
                                @RequestParam("transfer") BigInteger transfer) {
        return bankAccountService.transferMoney(accountNr, accountNr2, transfer);
    }
    //localhost:8080/transfer/111111/222222?transfer=45 TÄIENDATUD


    @GetMapping("clientlist")       //Tagastab terve klientide nimekiri
    public List<BankAccount> clientlist() {
        return bankAccountService.clientlist();

    }
    //localhost:8080/clientlist
}
