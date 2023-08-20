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

    @Test
    void getSaveAndReturnExchangeRateRequests() {
        //given
        given(exchangeRateClient.getExchangeRateFor(Currency.of("USD")))
                .willReturn(new ExchangeRate(new BigDecimal("4.4444")));
        given(exchangeRateClient.getExchangeRateFor(Currency.of("SEK")))
                .willReturn(new ExchangeRate(new BigDecimal("0.3333")));

        //when
        final ExchangeRate exchangeRateMC = service.getExchangeRateFor("USD", "Mantis Crab");
        final ExchangeRate exchangeRateJS = service.getExchangeRateFor("SEK", "John Smith");

        //then
        Assertions.assertEquals(new BigDecimal("4.4444"), exchangeRateMC.getValue());
        Assertions.assertEquals(new BigDecimal("0.3333"), exchangeRateJS.getValue());

        //when
        final List<ExchangeRateRequestDto> requests = service.getAllExchangeRateRequests();

        //then
        Assertions.assertEquals(2, requests.size());

        final ExchangeRateRequestDto mantisCrab = requests.stream().filter(
                        request -> request.getName().equals("Mantis Crab"))
                .findFirst().orElseThrow();
        Assertions.assertEquals("Mantis Crab", mantisCrab.getName());
        Assertions.assertEquals("USD", mantisCrab.getCurrency());
        Assertions.assertEquals(new BigDecimal("4.4444"), mantisCrab.getValue());
        Assertions.assertEquals(LocalDateTime.now(clock).truncatedTo(ChronoUnit.MICROS), mantisCrab.getRequestDate());

        final ExchangeRateRequestDto johnSmith = requests.stream().filter(
                        request -> request.getName().equals("John Smith"))
                .findFirst().orElseThrow();
        Assertions.assertEquals("John Smith", johnSmith.getName());
        Assertions.assertEquals("SEK", johnSmith.getCurrency());
        Assertions.assertEquals(new BigDecimal("0.3333"), johnSmith.getValue());
        Assertions.assertEquals(LocalDateTime.now(clock).truncatedTo(ChronoUnit.MICROS), johnSmith.getRequestDate());
    }
}