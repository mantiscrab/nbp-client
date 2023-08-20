package pl.mantiscrab.nbpclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class GetExchangeRateAndSaveServiceTest {
    @Autowired
    GetExchangeRateAndSaveService service;

    @Test
    void getSaveAndReturnExchangeRateRequests() {
        //given

        //when
        service.getExchangeRateFor("USD", "Mantis Crab");
        service.getExchangeRateFor("PLN", "John Smith");
        final List<ExchangeRateRequestDto> requests = service.getAllExchangeRateRequests();

        //then
        final ExchangeRateRequestDto mantisCrab = requests.stream().filter(
                        request -> request.name().equals("Mantis Crab"))
                .findFirst().orElseThrow();
        Assertions.assertEquals("Mantis Crab", mantisCrab.name());
        Assertions.assertEquals("USD", mantisCrab.currency());
        Assertions.assertEquals(new BigDecimal("4.25"), mantisCrab.value());
        Assertions.assertEquals(LocalDateTime.now(), mantisCrab.requestDate());

        final ExchangeRateRequestDto johnSmith = requests.stream().filter(
                        request -> request.name().equals("John Smith"))
                .findFirst().orElseThrow();
        Assertions.assertEquals("John Smith", johnSmith.name());
        Assertions.assertEquals("EUR", johnSmith.currency());
        Assertions.assertEquals(new BigDecimal("5.00"), johnSmith.value());
        Assertions.assertEquals(LocalDateTime.now(), johnSmith.requestDate());
    }
}