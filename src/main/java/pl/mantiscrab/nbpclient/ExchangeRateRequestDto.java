package pl.mantiscrab.nbpclient;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExchangeRateRequestDto(String currency, String name, LocalDateTime requestDate, BigDecimal value) {
}
