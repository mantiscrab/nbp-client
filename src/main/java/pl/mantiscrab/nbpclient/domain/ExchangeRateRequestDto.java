package pl.mantiscrab.nbpclient.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class ExchangeRateRequestDto {
    private final String currency;
    private final String name;
    private final LocalDateTime requestDate;
    private final BigDecimal value;
}
