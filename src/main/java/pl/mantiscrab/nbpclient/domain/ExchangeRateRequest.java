package pl.mantiscrab.nbpclient.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import pl.mantiscrab.nbpclient.domain.exceptions.ExchangeRateException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Getter
@Entity
class ExchangeRateRequest {
    @Id
    private UUID id;
    @Getter
    private Currency currency;
    @Getter
    private String name;
    @Getter
    private LocalDateTime requestDate;

    private ExchangeRate exchangeRate;

    protected ExchangeRateRequest() {
    }

    ExchangeRateRequest(final Currency currency, final String name, final LocalDateTime requestDate, final ExchangeRate exchangeRate) {
        if (currency == null) throw new ExchangeRateException("Currency must not be empty");
        if (name == null || name.isEmpty()) throw new ExchangeRateException("Name must not be empty");
        if (requestDate == null) throw new ExchangeRateException("Creation date must not be empty");
        if (exchangeRate == null) throw new ExchangeRateException("Exchange rate must not be empty");

        this.id = UUID.randomUUID();
        this.currency = currency;
        this.name = name;
        this.requestDate = requestDate;
        this.exchangeRate = exchangeRate;
    }

    ExchangeRateRequest(final Currency currency, final String name, final LocalDateTime requestDate) {
        if (currency == null) throw new ExchangeRateException("Currency must not be empty");
        if (name == null || name.isEmpty()) throw new ExchangeRateException("Name must not be empty");
        if (requestDate == null) throw new ExchangeRateException("Creation date must not be empty");

        this.id = UUID.randomUUID();
        this.currency = currency;
        this.name = name;
        this.requestDate = requestDate;
        this.exchangeRate = null;
    }

    Optional<ExchangeRate> getExchangeRate() {
        return Optional.ofNullable(exchangeRate);
    }
}
