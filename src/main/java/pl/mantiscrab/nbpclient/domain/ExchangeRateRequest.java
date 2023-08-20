package pl.mantiscrab.nbpclient.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
class ExchangeRateRequest {
    @Id
    private UUID id;
    private Currency currency;
    private String name;
    private LocalDateTime requestDate;
    private ExchangeRate exchangeRate;

    ExchangeRateRequest(final UUID id, final Currency currency, final String name, final LocalDateTime requestDate, final ExchangeRate exchangeRate) {
        this.id = id;
        this.currency = currency;
        this.name = name;
        this.requestDate = requestDate;
        this.exchangeRate = exchangeRate;
    }

    protected ExchangeRateRequest() {

    }
}
