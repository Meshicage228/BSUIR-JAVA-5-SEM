package by.meshicage.util.factories;

import by.meshicage.entity.bank.BankAccount;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountFactory {

    public BankAccount createBankAccount(String accountNumber, Double balance) {
        return BankAccount.builder()
                .accountNumber(accountNumber)
                .balance(balance)
                .build();
    }
}