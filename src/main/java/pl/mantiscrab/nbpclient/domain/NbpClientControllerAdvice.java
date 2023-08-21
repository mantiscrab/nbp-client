package pl.mantiscrab.nbpclient.domain;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NbpClientControllerAdvice {


    @ExceptionHandler(NbpClientException.class)
    ResponseEntity<String> handleNbpClientException(NbpClientException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());
    }
}
