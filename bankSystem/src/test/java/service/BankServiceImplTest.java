package service;

import by.meshicage.db.InMemoryDatabase;
import by.meshicage.entity.bank.BankAccount;
import by.meshicage.entity.clients.abstr.Client;
import by.meshicage.entity.clients.impl.SimpleBankClient;
import by.meshicage.util.factories.AccountFactory;
import by.meshicage.util.factories.ClientFactory;
import by.meshicage.service.impl.BankServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayName("BankService tests")
@ExtendWith(MockitoExtension.class)
public class BankServiceImplTest {

    @Mock
    private InMemoryDatabase database;

    @InjectMocks
    private BankServiceImpl bankService;

    private static final String CARD_NUMBER = "1234-5678-9012-3456";
    private static final String NAME = "John Doe";
    private static final String ACCOUNT_NUMBER = "123456789";

    @Test
    @DisplayName("Client found: return balance")
    void checkBalance() {
        // Given
        Client client = ClientFactory.createSimpleClient(1, NAME, CARD_NUMBER,
                AccountFactory.createBankAccount(ACCOUNT_NUMBER, 1000.0));
        when(database.getClientByCardNumber(anyString())).thenReturn(Optional.of(client));

        // When
        Double balance = bankService.checkBalance(CARD_NUMBER);

        // Then
        assertEquals(1000.0, balance);
    }

    @Test
    @DisplayName("Client not found: exception thrown")
    void clientNotFound() {
        // Given
        when(database.getClientByCardNumber(anyString())).thenReturn(Optional.empty());

        // When / Then
        assertThrows(RuntimeException.class, () -> bankService.checkBalance(CARD_NUMBER));
    }

    @Test
    @DisplayName("transferFunds should throw RuntimeException when sender has insufficient funds")
    void transferFunds_ShouldThrowException_WhenInsufficientFunds() {
        // Given
        SimpleBankClient build = SimpleBankClient.builder()
                .account(AccountFactory.createBankAccount(ACCOUNT_NUMBER, 1000.0))
                .id(1)
                .cardNumber(CARD_NUMBER)
                .name(NAME)
                .build();

        when(database.getClientByCardNumber(anyString())).thenReturn(Optional.of(build));

        // When / Then
         assertThrows(Error.class, () -> {
            bankService.transferFunds(CARD_NUMBER, ACCOUNT_NUMBER, 2000.0);
        });
    }
}