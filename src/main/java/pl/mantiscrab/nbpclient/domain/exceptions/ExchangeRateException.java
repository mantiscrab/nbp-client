package pl.mantiscrab.nbpclient.domain.exceptions;

import org.springframework.http.HttpStatus;
import pl.mantiscrab.nbpclient.domain.NbpClientException;

public class ExchangeRateException extends NbpClientException {
    public ExchangeRateException(final String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
