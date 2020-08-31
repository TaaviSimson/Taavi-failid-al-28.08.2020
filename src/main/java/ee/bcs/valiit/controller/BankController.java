package ee.bcs.valiit.controller;

import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;


@RestController
public class BankController {

    private final Map<String, BigInteger> account = new HashMap<>();

    @PostMapping("account")       //Uue pangakonto loomine  TÖÖTAB
    public void createAccount(@RequestBody BankAccount bankAccount) {
        //BankAccount on asukoht (Klass) ja bankAccount on muutuja
        account.put(bankAccount.getAccountNr(), bankAccount.getAccountBal());   //HashMap puhul put, mitte add!
    }
    //kuna Map, siis bankAccount.getAccount kontonumbri saamiseks
    //localhost:8080/account - Postman programmi kaudu


    @GetMapping("bankbalance/{accountNr}")     //Tagastab pangakonto seisu  TÖÖTAB
    public BigInteger getAccount(@PathVariable("accountNr") String accountNr) {
        return account.get(accountNr);  //Siin accountNr, sest Map, leiab ise vastava paari välja
    }
    //localhost:8080/bankbalance/335445


    @PutMapping("deposit/{id}")     //Kannab loodud kontole raha
    public void depositMoney(@PathVariable("id") int id,@RequestBody BankAccount bankAccount){
        account.put(id, bankAccount.getAccountBal());
    }
    //localhost:8080/deposit/1 - kannab kontole 1 raha Postmani kaudu

    @PutMapping("withdraw/{id}")     //Võtab loodud kontolt raha
    public void withdrawMoney(@PathVariable("id") int id,@RequestBody BankAccount bankAccount) {
        account.put(id, bankAccount.getAccountBal());
    }

    @PutMapping("transfer/{id}")     //Viib raha ühelt kontolt teisele
    public void transferMoney(@PathVariable("id") int id,@RequestBody BankAccount bankaccount) {
        account.put(id, bankaccount.getAccountBal());
    }
}


