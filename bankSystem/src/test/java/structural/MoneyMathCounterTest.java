package structural;

import by.meshicage.service.impl.MoneyMathCounter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("MoneyMathCounter")
public class MoneyMathCounterTest {

    private static final List<Double> AMOUNTS = List.of(100.0, 200.0, 300.0);

    @Test
    @DisplayName("MoneyMathCounter should calculate sum and max correctly")
    void moneyMathCounterTest() {
        // Given
        MoneyMathCounter moneyMathCounter = new MoneyMathCounter(AMOUNTS);

        // When / then
        moneyMathCounter.execute();
    }
}