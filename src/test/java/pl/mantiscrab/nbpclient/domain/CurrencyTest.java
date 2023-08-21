package pl.mantiscrab.nbpclient.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.mantiscrab.nbpclient.domain.exceptions.CurrencyCreationException;

class CurrencyTest {
    @Test
    void shouldCreateCurrency() {
        final Currency usd = Currency.of("USD");
        Assertions.assertEquals("USD", usd.asString());
    }
    @Test
    void shouldNotCreateCurrency() {
        Assertions.assertThrows(CurrencyCreationException.class,
                () -> Currency.of("US"));
        Assertions.assertThrows(CurrencyCreationException.class,
                () -> Currency.of("USDU"));
        Assertions.assertThrows(CurrencyCreationException.class,
                () -> Currency.of("us"));
        Assertions.assertThrows(CurrencyCreationException.class,
                () -> Currency.of("usd"));
        Assertions.assertThrows(CurrencyCreationException.class,
                () -> Currency.of("usdu"));
        Assertions.assertThrows(CurrencyCreationException.class,
                () -> Currency.of(""));
        Assertions.assertThrows(CurrencyCreationException.class,
                () -> Currency.of(null));
    }
}