package ee.bcs.valiit.controller;

import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;


@RestController
public class BankController {

    private final Map<String, BigInteger> ALL_ACCOUNTS = new HashMap<>();
    //

    @PostMapping("account")       //Uue pangakonto loomine  TÖÖTAB
    public void createAccount(@RequestBody BankAccount bankAccount) {
        //BankAccount on asukoht (Klass) ja bankAccount on muutuja
        ALL_ACCOUNTS.put(bankAccount.getAccountNr(), bankAccount.getAccountBal());   //HashMap puhul put, mitte add!
    }
    //kuna Map, siis bankAccount.getAccount kontonumbri saamiseks
    //localhost:8080/account - Postman programmi kaudu


    @GetMapping("bankbalance/{accountNr}")     //Tagastab pangakonto seisu  TÖÖTAB
    public BigInteger getAccount(@PathVariable("accountNr") String accountNr) {
        return ALL_ACCOUNTS.get(accountNr);  //Siin accountNr, sest Map, leiab ise vastava paari välja
    }
    //localhost:8080/bankbalance/335445


    @PutMapping("deposit/{accountNr}")     //Kannab loodud kontole raha     TÖÖTAB
    public void depositMoney(@PathVariable("accountNr") String accountNr,
                             @RequestParam("deposit") BigInteger deposit) {
                            //Küsib sisendit juurdekantava summa kohta
        BigInteger accountBal = ALL_ACCOUNTS.get(accountNr);
        //Defineerime accountBal, ALL-ACOUNTS võtab info hashmapist kohal accountNr
        accountBal = accountBal.add(deposit);           //Lisab kontole summa "deposit"
        ALL_ACCOUNTS.put(accountNr, accountBal);        //Kirjutab Mapis kontol oleva summa üle
    }
    //localhost:8080/deposit/123456?deposit=123 - kannab kontole 123 raha Postmani kaudu

    @PutMapping("withdraw/{id}")     //Võtab loodud kontolt raha
    public void withdrawMoney(@PathVariable("id") int id,@RequestBody BankAccount bankAccount) {
        ALL_ACCOUNTS.put(id, bankAccount.getAccountBal());

    }


    @PutMapping("transfer/{id}")     //Viib raha ühelt kontolt teisele
    public void transferMoney(@PathVariable("id") int id,@RequestBody BankAccount bankaccount) {
        ALL_ACCOUNTS.put(id, bankaccount.getAccountBal());
    }

}


