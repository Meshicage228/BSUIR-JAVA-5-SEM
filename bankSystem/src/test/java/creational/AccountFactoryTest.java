package creational;

import by.meshicage.entity.bank.BankAccount;
import by.meshicage.entity.clients.abstr.Client;
import by.meshicage.util.factories.AccountFactory;
import by.meshicage.util.factories.ClientFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("AccountFactory unit tests:")
public class AccountFactoryTest {

    @ParameterizedTest
    @DisplayName("createSimpleClient should return a valid Client object")
    @MethodSource("provideClientTestData")
    void createClients(int id, String name, String cardNumber, BankAccount account) {
        // Given / When
        Client client = ClientFactory.createSimpleClient(id, name, cardNumber, account);

        // Then
        assertNotNull(client);
        assertEquals(id, client.getId());
        assertEquals(name, client.getName());
        assertEquals(cardNumber, client.getCardNumber());
        assertEquals(account, client.getAccount());
    }

    private static Stream<Arguments> provideClientTestData() {
        BankAccount account1 = AccountFactory.createBankAccount("123456789", 1000.0);
        BankAccount account2 = AccountFactory.createBankAccount("987654321", 500.0);

        return Stream.of(
                Arguments.of(1, "John Doe", "1111-2222-3333-4444", account1),
                Arguments.of(2, "Jane Doe", "5555-6666-7777-8888", account2),
                Arguments.of(3, "Alice", "9999-0000-1111-2222", null)
        );
    }
}