package pl.mantiscrab.nbpclient;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class GetExchangeRateAndSaveService {

    @Transactional
    public ExchangeRate getExchangeRateFor(final String currency, final String fullName) {
        return null;
    }

    public List<ExchangeRateRequestDto> getAllExchangeRateRequests() {
        return null;
    }
}