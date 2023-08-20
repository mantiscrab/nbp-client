package pl.mantiscrab.nbpclient.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ExchangeRate {

    @Column(name = "exchange_rate", columnDefinition = "numeric(20,4)")
    private BigDecimal value;

    public ExchangeRate(final BigDecimal value) {
        this.value = value;
    }
}
