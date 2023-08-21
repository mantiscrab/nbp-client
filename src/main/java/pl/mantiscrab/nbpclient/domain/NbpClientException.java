package pl.mantiscrab.nbpclient.domain;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter

public class NbpClientException extends RuntimeException {
    private final HttpStatus httpStatus;

    public NbpClientException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
