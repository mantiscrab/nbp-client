package pl.mantiscrab.nbpclient.domain;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import pl.mantiscrab.nbpclient.domain.exceptions.CurrencyCreationException;

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
            throw new CurrencyCreationException("Currency name cannot be null");
        if (currency.length() != 3)
            throw new CurrencyCreationException("Currency name must consist of exactly 3 letters");
        for (final char c : currency.toCharArray()) {
            if (!Character.isAlphabetic(c) || !Character.isUpperCase(c))
                throw new CurrencyCreationException("Invalid currency name: " + currency);
        }
        return new Currency(currency);
    }

    public String asString() {
        return currency;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currency='" + currency + '\'' +
                '}';
    }
}
