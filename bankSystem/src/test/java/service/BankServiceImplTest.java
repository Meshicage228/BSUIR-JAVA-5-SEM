package service;

import by.meshicage.db.InMemoryDatabase;
import by.meshicage.entity.clients.abstr.Client;
import by.meshicage.factories.AccountFactory;
import by.meshicage.factories.ClientFactory;
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

    @Test
    @DisplayName("Client found: return balance")
    void checkBalance() {
        // Given
        Client client = ClientFactory.createSimpleClient(1, "John Doe", CARD_NUMBER,
                AccountFactory.createBankAccount("123456789", 1000.0));
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
}