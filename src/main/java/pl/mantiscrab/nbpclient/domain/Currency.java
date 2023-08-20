package pl.mantiscrab.nbpclient.domain;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class Currency {
    private String currency;

    private Currency(final String currency) {
        this.currency = currency;
    }

    protected Currency() {
    }

    public static Currency of(String currency) {
        if (currency == null)
            throw new IllegalArgumentException();
        if (currency.length() != 3)
            throw new IllegalArgumentException();
        for (final char c : currency.toCharArray()) {
            if (!Character.isAlphabetic(c) || !Character.isUpperCase(c))
                throw new IllegalArgumentException();
        }
        return new Currency(currency);
    }

    public String asString() {
        return currency;
    }
}
