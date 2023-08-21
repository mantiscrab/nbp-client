package pl.mantiscrab.nbpclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import pl.mantiscrab.nbpclient.domain.*;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ActiveProfiles("test")
@SpringBootTest
class GetExchangeRateAndSaveServiceTest {
    @Autowired
    Clock clock;
    @Autowired
    GetExchangeRateAndSaveService service;
    @MockBean
    ExchangeRateClient exchangeRateClient;

    final Currency usd = Currency.of("USD");
    final BigDecimal usdExchangeRateValue = new BigDecimal("4.4444");
    final ExchangeRate usdExchangeRate = new ExchangeRate(usdExchangeRateValue);
    final Currency sek = Currency.of("SEK");
    final BigDecimal sekExchangeRateValue = new BigDecimal("0.3333");
    final ExchangeRate sekExchangeRate = new ExchangeRate(sekExchangeRateValue);

    @Test
    void getSaveAndReturnExchangeRateRequests() {
        //given
        given(exchangeRateClient.getExchangeRateFor(usd))
                .willReturn(usdExchangeRate);
        given(exchangeRateClient.getExchangeRateFor(sek))
                .willReturn(sekExchangeRate);

        //when
        final ExchangeRate exchangeRateMC = service.getExchangeRateFor("USD", "Mantis Crab");
        final ExchangeRate exchangeRateJS = service.getExchangeRateFor("SEK", "John Smith");

        //then
        Assertions.assertEquals(usdExchangeRateValue, exchangeRateMC.getValue());
        Assertions.assertEquals(sekExchangeRateValue, exchangeRateJS.getValue());

        //when
        final List<ExchangeRateRequestDto> requests = service.getAllExchangeRateRequests();

        //then
        Assertions.assertEquals(2, requests.size());

        final ExchangeRateRequestDto mantisCrab = getFirstExchangeRateBy("Mantis Crab", requests);
        Assertions.assertEquals("Mantis Crab", mantisCrab.getName());
        Assertions.assertEquals("USD", mantisCrab.getCurrency());
        Assertions.assertEquals(usdExchangeRateValue, mantisCrab.getValue());
        Assertions.assertEquals(LocalDateTime.now(clock).truncatedTo(ChronoUnit.MICROS), mantisCrab.getRequestDate());

        final ExchangeRateRequestDto johnSmith = getFirstExchangeRateBy("John Smith", requests);
        Assertions.assertEquals("John Smith", johnSmith.getName());
        Assertions.assertEquals("SEK", johnSmith.getCurrency());
        Assertions.assertEquals(sekExchangeRateValue, johnSmith.getValue());
        Assertions.assertEquals(LocalDateTime.now(clock).truncatedTo(ChronoUnit.MICROS), johnSmith.getRequestDate());
    }

    private static ExchangeRateRequestDto getFirstExchangeRateBy(final String name, final List<ExchangeRateRequestDto> requests) {
        return requests.stream().filter(
                        request -> request.getName().equals(name))
                .findFirst().orElseThrow();
    }
}