package structural;

import by.meshicage.service.abstr.BankService;
import by.meshicage.service.facade.BankFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("BankFacade unit tests:")
public class BankFacadeTest {

    private static final String SENDER_CARD = "1234-5678-9012-3456";
    private static final String RECEIVER_CARD = "9876-5432-1098-7654";
    private static final String SENDER_NAME = "John Doe";
    private static final Double AMOUNT = 200.0;

    @Mock
    private BankService bankService;

    @InjectMocks
    private BankFacade bankFacade;

    @Test
    @DisplayName("performTransfer should return true when transfer is successful")
    void performTransferShouldReturnSuccess() {
        // Given
        when(bankService.validateCredentials(anyString(), anyString())).thenReturn(true);
        when(bankService.transferFunds(anyString(), anyString(), anyDouble())).thenReturn(true);

        // When
        boolean result = bankFacade.performTransfer(SENDER_CARD, RECEIVER_CARD, AMOUNT, SENDER_NAME);

        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("checkClientBalance should return balance when client is found")
    void checkClientBalanceShouldReturnBalance() {
        // Given
        Double balance = 1000.0;
        when(bankService.checkBalance(anyString())).thenReturn(balance);

        // When
        Double result = bankFacade.checkClientBalance(SENDER_CARD);

        // Then
        assertEquals(balance, result);
    }
}