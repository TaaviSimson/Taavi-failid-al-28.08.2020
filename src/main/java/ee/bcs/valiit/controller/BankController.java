package ee.bcs.valiit.controller;

import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;


@RestController
public class BankController {

    private final Map<String, BigInteger> allAccounts = new HashMap<>();
    //HashMap kustub peale iga serveri uuendust!

    @PostMapping("account")       //Uue pangakonto loomine  TÖÖTAB
    public void createAccount(@RequestBody BankAccount request) {
        //BankAccount on asukoht (Klass) ja request on muutuja
        //kuna @RequestBody, siis request.getAccount kontonumbri saamiseks
        //HashMap puhul put, mitte add!
        allAccounts.put(request.getAccountNr(), request.getAccountBal());
    }
    //localhost:8080/account - Postman programmi kaudu


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