package pl.mantiscrab.nbpclient.domain.exceptions;

import org.springframework.http.HttpStatus;
import pl.mantiscrab.nbpclient.domain.NbpClientException;

public class CurrencyCreationException extends NbpClientException {
    public CurrencyCreationException(final String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
