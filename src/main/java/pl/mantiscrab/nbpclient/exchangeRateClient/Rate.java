package pl.mantiscrab.nbpclient.exchangeRateClient;

import java.math.BigDecimal;

record Rate(String no, String effectiveDate, BigDecimal mid) {
}
