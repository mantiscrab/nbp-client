package pl.mantiscrab.nbpclient.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.mantiscrab.nbpclient.domain.exceptions.ExchangeRateException;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

class ExchangeRateRequestTest {
    private static final Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    private static final Currency USD = Currency.of("USD");
    private static final String MANTISCRAB = "mantiscrab";
    private static final LocalDateTime requestDate = LocalDateTime.now(clock);
    private static final ExchangeRate EXCHANGE_RATE = new ExchangeRate(BigDecimal.ONE);

    @Test
    void shouldCreateExchangeRateRequestWithValue() {
        final ExchangeRateRequest exchangeRateRequest = new ExchangeRateRequest(USD, MANTISCRAB,
                requestDate, EXCHANGE_RATE);
        Assertions.assertNotNull(exchangeRateRequest.getId());
        Assertions.assertEquals(LocalDateTime.now(clock), exchangeRateRequest.getRequestDate());
        Assertions.assertEquals(EXCHANGE_RATE, exchangeRateRequest.getExchangeRate().orElseThrow());
        Assertions.assertEquals(MANTISCRAB, exchangeRateRequest.getName());
        Assertions.assertEquals(USD, exchangeRateRequest.getCurrency());
    }

    @Test
    void shouldCreateExchangeRateRequestWithNullValue() {
        final ExchangeRateRequest exchangeRateRequest = new ExchangeRateRequest(USD, MANTISCRAB,
                LocalDateTime.now(clock));
        Assertions.assertNotNull(exchangeRateRequest.getId());
        Assertions.assertEquals(LocalDateTime.now(clock), exchangeRateRequest.getRequestDate());
        Assertions.assertEquals(Optional.empty(), exchangeRateRequest.getExchangeRate());
        Assertions.assertEquals(MANTISCRAB, exchangeRateRequest.getName());
        Assertions.assertEquals(USD, exchangeRateRequest.getCurrency());
    }

    @Test
    void shouldNotCreateExchangeRateRequest() {
        Assertions.assertThrows(ExchangeRateException.class,
                () -> new ExchangeRateRequest(null, MANTISCRAB, requestDate, EXCHANGE_RATE));
        Assertions.assertThrows(ExchangeRateException.class,
                () -> new ExchangeRateRequest(USD, null, requestDate, EXCHANGE_RATE));
        Assertions.assertThrows(ExchangeRateException.class,
                () -> new ExchangeRateRequest(USD, "", requestDate, EXCHANGE_RATE));
        Assertions.assertThrows(ExchangeRateException.class,
                () -> new ExchangeRateRequest(USD, MANTISCRAB, null, EXCHANGE_RATE));
        Assertions.assertThrows(ExchangeRateException.class,
                () -> new ExchangeRateRequest(USD, MANTISCRAB, requestDate, null));
        Assertions.assertThrows(ExchangeRateException.class,
                () -> new ExchangeRateRequest(null, MANTISCRAB, requestDate));
        Assertions.assertThrows(ExchangeRateException.class,
                () -> new ExchangeRateRequest(USD, null, requestDate));
        Assertions.assertThrows(ExchangeRateException.class,
                () -> new ExchangeRateRequest(USD, "", requestDate));
        Assertions.assertThrows(ExchangeRateException.class,
                () -> new ExchangeRateRequest(USD, MANTISCRAB, null));
    }
}