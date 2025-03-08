package by.meshicage.service.impl;

import by.meshicage.service.abstr.AccountComponent;
import by.meshicage.service.abstr.AccountVisitor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AccountComposite implements AccountComponent {
    private final List<AccountComponent> accounts = new ArrayList<>();

    public void addAccount(AccountComponent account) {
        accounts.add(account);
    }

    @Override
    public void display() {
        System.out.println("Composite Account:");
        accounts.forEach(AccountComponent::display);
    }

    @Override
    public double getBalance() {
        return accounts.stream()
                       .mapToDouble(AccountComponent::getBalance)
                       .sum();
    }

    @Override
    public void accept(AccountVisitor visitor) {
        accounts.forEach(a -> a.accept(visitor));
        visitor.visit(this);
    }
}