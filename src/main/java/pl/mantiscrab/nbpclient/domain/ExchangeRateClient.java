package pl.mantiscrab.nbpclient.domain;


public interface ExchangeRateClient {
    ExchangeRate getExchangeRateFor(Currency currency);
}
