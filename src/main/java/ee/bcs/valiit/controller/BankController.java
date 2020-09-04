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

    @PostMapping("createclient")
    public void createClient(@RequestBody CreateClientRequest clientRequest) {
        bankAccountService.createClient(clientRequest.getFirstName(),
                clientRequest.getLastName());
    }
    //localhost:8080/createclient
    //Getter ja Setter muutujatega CreateClientRequest klassis

    @PostMapping("createaccount")  //Uue pangakonto loomine TÖÖTAB
    public void createAccount(@RequestBody CreateAccountRequest accountRequest) {
        bankAccountService.createAccount(accountRequest.getAccountNr(),
                accountRequest.getBalance(),
                accountRequest.getClientId());
    }
    //localhost:8080/createaccount
    //Getter ja Setter CreateAccountRequest

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