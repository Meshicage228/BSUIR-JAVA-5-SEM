package by.meshicage.service.abstr;

import by.meshicage.service.impl.AccountComposite;
import by.meshicage.entity.bank.BankAccount;

public interface AccountVisitor {
    void visit(BankAccount account);
    void visit(AccountComposite accountComposite);
}