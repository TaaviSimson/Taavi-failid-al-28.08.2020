package ee.bcs.valiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
public class BankController {
    //Kriipsutades RestControlleri välja koodi ei vaata

    @Autowired
    private BankAccountService bankAccountService;
    //Vajalik BankAccountService klassiga ühenduse loomiseks

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @PostMapping("createaccount/{accountNr}/{balance}/{clientId}")  //Uue pangakonto loomine TÖÖTAB
    public void createAccount(@PathVariable("accountNr") String accountNr,
                              @PathVariable("balance") BigInteger balance,
                              @PathVariable("clientId") BigInteger clientId) {
        bankAccountService.createAccount(accountNr, balance, clientId);
    }
    //localhost:8080/createaccount/111111/12345/1000

    @GetMapping("bankbalance/{accountNr}")      //Ühe kliendi pangajäägi vaatamine  TÖÖTAB
    public BigInteger getAccount(@PathVariable("accountNr") String accountNr) {
        return bankAccountService.bankBalance(accountNr);
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