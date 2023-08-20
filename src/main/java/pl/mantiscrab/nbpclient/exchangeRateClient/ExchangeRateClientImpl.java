package pl.mantiscrab.nbpclient.exchangeRateClient;

import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.mantiscrab.nbpclient.domain.Currency;
import pl.mantiscrab.nbpclient.domain.ExchangeRateClient;

import java.net.URI;

@AllArgsConstructor
class ExchangeRateClientImpl implements ExchangeRateClient {
    private final RestTemplate restTemplate;

    @Override
    public pl.mantiscrab.nbpclient.domain.ExchangeRate getExchangeRateFor(final Currency currency) {
        final URI uri = UriComponentsBuilder.fromUriString("https://api.nbp.pl/api/exchangerates/rates/A")
                .path("/" + currency.asString())
                .queryParam("format", "json")
                .build().toUri();
        final ExchangeRate forObject = restTemplate.getForObject(uri, ExchangeRate.class);
        return new pl.mantiscrab.nbpclient.domain.ExchangeRate(forObject.rates().get(0).mid());
    }
}
