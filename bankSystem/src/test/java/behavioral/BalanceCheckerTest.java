package behavioral;

import by.meshicage.entity.bank.BankAccount;
import by.meshicage.factories.AccountFactory;
import by.meshicage.service.impl.AccountComposite;
import by.meshicage.service.impl.BalanceChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BalanceChecker unit Tests:")
public class BalanceCheckerTest {

    private static final String ACCOUNT_NUMBER = "123456789";
    private static final Double BALANCE = 1000.0;
    private BalanceChecker checker;
    private AccountComposite account;

    @BeforeEach
    void setUp() {
        checker = new BalanceChecker();
        account = new AccountComposite();
    }

    @Test
    @DisplayName("visit(BankAccount) should print account balance")
    void printAccountBalance() {
        // Given
        BankAccount account = AccountFactory.createBankAccount(ACCOUNT_NUMBER, BALANCE);

        // When / Then
        checker.visit(account);
    }

    @Test
    @DisplayName("visit(AccountComposite) should calculate total and min balance")
    void calculateTotalAndMinBalance() {
        // Given
        account.addAccount(AccountFactory.createBankAccount("123456789", 1000.0));
        account.addAccount(AccountFactory.createBankAccount("987654321", 500.0));

        // When / Then
        checker.visit(account);
    }
}