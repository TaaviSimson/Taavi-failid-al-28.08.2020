package ee.bcs.valiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class BankAccountService {
//@Service juurde tuleb äri loogika (if, else käsude jne)

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public void createClient(String firstname, String lastname){
        bankAccountRepository.createClient(firstname, lastname);
    }

    public void createAccount(String accountNr, BigInteger balance, BigInteger clientId){
        bankAccountRepository.createAccount(accountNr, balance, clientId);
    }
    //Loob uue konto

    public BigInteger bankBalance(String accountNr){
        return bankAccountRepository.getBalance(accountNr);
    }
    //Ühe kliendi kontoseisu vaatamine kontonumbri alusel

    public void depositMoney(String accountNr, BigInteger deposit){
        bankAccountRepository.updateBalancePlus(accountNr,deposit);
        bankAccountRepository.transferHistory(accountNr, deposit);
    }
    //Paneb kontole raha juurde

    public String withdrawMoney(String accountNr, BigInteger withdraw) {
        BigInteger accountBal = bankAccountRepository.getBalance(accountNr);
        if (accountBal.compareTo(withdraw) > 0) {
            bankAccountRepository.updateBalanceMinus(accountNr, withdraw);
            bankAccountRepository.transferHistory2(accountNr, withdraw);
            return "Transfer successful.";
        } else {
            return "Transfer failed. You don`t have enough money.";
        }
    }
    //võtab kontolt raha välja kui on kontol piisavalt raha

    public String transferMoney(String accountNr, String accountNr2, BigInteger transfer) {
        BigInteger accountBal = bankAccountRepository.getBalance(accountNr);
        if (accountBal.compareTo(transfer) > 0) {
            bankAccountRepository.updateBalanceMinus(accountNr, transfer);       //updateBalance lahutab maha raha
            bankAccountRepository.updateBalancePlus(accountNr2, transfer);      //updateBalance2 liidab raha juurde
            bankAccountRepository.transferHistory3(accountNr, accountNr2, transfer);
            return "Transfer successful.";
        } else {
            return "Transfer failed. You don`t have enough money.";
        }
    }
    //Kannab raha kontolt 1 kontole 2 kui kontol1 on piisavalt raha

    public List<BankAccount> clientlist() {
        return bankAccountRepository.clientlist();  //Siin vaja kohe return
    }
    //Annab välja klientide nimekirja
}