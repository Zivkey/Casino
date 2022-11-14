package Classes;

import Exceptions.ExchangeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeTest {

    @Test
    void setExchangeRateNormal() throws ExchangeException {
        Exchange exchange = new Exchange();
        exchange.setExchangeRate(1.54);
    }

    @Test
    void setExchangeRateMinus() {
        Exchange exchange = new Exchange();
        Assertions.assertThrows(ExchangeException.class, () -> {
            exchange.setExchangeRate(-1.230);
        });
    }

    @Test
    void setExchangeRateZero() {
        Exchange exchange = new Exchange();
        Assertions.assertThrows(ExchangeException.class, () -> {
            exchange.setExchangeRate(0);
        });
    }
}