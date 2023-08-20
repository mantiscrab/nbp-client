package pl.mantiscrab.nbpclient.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class GetExchangeRateAndSaveService {
    private final ExchangeRateClient exchangeRateClient;
    private final ExchangeRateRequestRepository requestRepository;
    private final Clock clock;

    @Transactional
    public ExchangeRate getExchangeRateFor(final String currencyName, final String fullName) {
        final Currency currency = Currency.of(currencyName);
        final ExchangeRate exchangeRate = exchangeRateClient.getExchangeRateFor(currency);
        final ExchangeRateRequest exchangeRateRequest
                = new ExchangeRateRequest(UUID.randomUUID(), currency, fullName,
                LocalDateTime.now(clock), exchangeRate);
        requestRepository.save(exchangeRateRequest);
        return exchangeRate;
    }

    public List<ExchangeRateRequestDto> getAllExchangeRateRequests() {
        return requestRepository.findAll().stream().map(
                r -> new ExchangeRateRequestDto(
                        r.getCurrency().asString(),
                        r.getName(),
                        r.getRequestDate(),
                        r.getExchangeRate().getValue()
                )).toList();
    }
}