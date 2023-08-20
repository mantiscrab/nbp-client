package pl.mantiscrab.nbpclient.exchangeRateClient;

import java.util.List;

record ExchangeRate(String table, String currency, String code, List<Rate> rates) {
}
