package behavioral;

import by.meshicage.util.factories.AccountFactory;
import by.meshicage.service.impl.AccountComposite;
import by.meshicage.service.impl.BalanceChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("AccountComposite unit Tests:")
public class AccountCompositeTest {

    private static final String ACCOUNT_NUMBER_1 = "123456789";
    private static final Double BALANCE_1 = 1000.0;
    private static final String ACCOUNT_NUMBER_2 = "987654321";
    private static final Double BALANCE_2 = 500.0;
    private AccountComposite accountComposite;
    private BalanceChecker balanceChecker;

    @BeforeEach
    void setUp() {
        accountComposite = new AccountComposite();
        balanceChecker = new BalanceChecker();
    }

    @Test
    @DisplayName("getBalance should return the sum of all account balances")
    void returnAllBalances() {
        // Given
        accountComposite.addAccount(AccountFactory.createBankAccount(ACCOUNT_NUMBER_1, BALANCE_1));
        accountComposite.addAccount(AccountFactory.createBankAccount(ACCOUNT_NUMBER_2, BALANCE_2));

        // When
        double totalBalance = accountComposite.getBalance();

        // Then
        assertEquals(BALANCE_1 + BALANCE_2, totalBalance);
    }

    @Test
    @DisplayName("accept should visit all accounts and the composite itself")
    void allAccountsAndCompositeItself() {
        // Given
        accountComposite.addAccount(AccountFactory.createBankAccount(ACCOUNT_NUMBER_1, BALANCE_1));
        accountComposite.addAccount(AccountFactory.createBankAccount(ACCOUNT_NUMBER_2, BALANCE_2));

        // When / Then
        accountComposite.accept(balanceChecker);
    }
}