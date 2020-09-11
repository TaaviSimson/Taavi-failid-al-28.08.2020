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

    public void createAccount(Integer accountNr, BigInteger balance, BigInteger clientId){
        bankAccountRepository.createAccount(accountNr, balance, clientId);
    }
    //Loob uue konto

    public BigInteger bankBalance(Integer accountNr){
        return bankAccountRepository.getBalance(accountNr);
    }
    //Ühe kliendi kontoseisu vaatamine kontonumbri alusel

    public void depositMoney(Integer accountNr, BigInteger deposit){
        bankAccountRepository.updateBalancePlus(accountNr,deposit);
        int id1 = bankAccountRepository.getUserId(accountNr);
        bankAccountRepository.transferHistory(id1, deposit);
    }
    //Paneb kontole raha juurde
    //transactio_history läheb id ja summa

    public String withdrawMoney(Integer accountNr, BigInteger withdraw) {
        BigInteger accountBal = bankAccountRepository.getBalance(accountNr);
        if (accountBal.compareTo(withdraw) > 0) {
            bankAccountRepository.updateBalanceMinus(accountNr, withdraw);
            int id1 = bankAccountRepository.getUserId(accountNr);
            bankAccountRepository.transferHistory2(id1, withdraw);
            return "Transfer successful.";
        } else {
            return "Transfer failed. You don`t have enough money.";
        }
    }
    //võtab kontolt raha välja kui on kontol piisavalt raha

    public String transferMoney(Integer accountNr, Integer accountNr2, BigInteger transfer) {
        BigInteger accountBal = bankAccountRepository.getBalance(accountNr);
        if (accountBal.compareTo(transfer) > 0) {
            bankAccountRepository.updateBalanceMinus(accountNr, transfer);       //updateBalance lahutab maha raha
            bankAccountRepository.updateBalancePlus(accountNr2, transfer);      //updateBalance2 liidab raha juurde
            int id1 = bankAccountRepository.getUserId(accountNr);
            int id2 = bankAccountRepository.getUserId(accountNr2);
            bankAccountRepository.transferHistory3(id1, id2, transfer);
            return "Transfer successful.";
        } else {
            return "Transfer failed. You don`t have enough money.";
        }
    }
    //Kannab raha kontolt 1 kontole 2 kui kontol1 on piisavalt raha

    public List<BankAccount> clientlist() {
        return bankAccountRepository.clientlist();
        //Siin vaja kohe return
    }
    //Annab välja klientide nimekirja


    public List<TransactionHistory> transactionHistory(Integer accountNr){
        return bankAccountRepository.transactionHistory(accountNr);
    }
    //Annab välja ühe konto maksete ajaloo
}