package creational;

import by.meshicage.entity.bank.BankAccount;
import by.meshicage.util.factories.AccountFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("ClientFactory unit tests:")
public class ClientFactoryTest {

    @ParameterizedTest
    @DisplayName("createBankAccount should return a valid BankAccount object")
    @MethodSource("provideBankAccountTestData")
    void createBankAccounts(String accountNumber, double balance) {
        // Given / When
        BankAccount account = AccountFactory.createBankAccount(accountNumber, balance);

        // Then
        assertNotNull(account);
        assertEquals(accountNumber, account.getAccountNumber());
        assertEquals(balance, account.getBalance());
    }

    private static Stream<Arguments> provideBankAccountTestData() {
        return Stream.of(
                Arguments.of("123456789", 1000.0),
                Arguments.of("987654321", 500.0),
                Arguments.of("111111111", 0.0)
        );
    }
}