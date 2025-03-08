package by.meshicage.service.impl;


import by.meshicage.entity.bank.BankAccount;
import by.meshicage.service.abstr.AccountComponent;
import by.meshicage.service.abstr.AccountVisitor;

public class BalanceChecker implements AccountVisitor {
    @Override
    public void visit(BankAccount account) {
        System.out.println("Checking balance for account: " + account.getAccountNumber() + ", Balance: " + account.getBalance());
    }

    @Override
    public void visit(AccountComposite accountComposite) {
        double totalBalance = accountComposite.getBalance();

        double minBalance = accountComposite.getAccounts().stream()
                .mapToDouble(AccountComponent::getBalance)
                .min()
                .orElse(0);

        System.out.println("Total balance: " + totalBalance);
        System.out.println("Min balance: " + minBalance);
    }
}