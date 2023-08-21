package pl.mantiscrab.nbpclient.domain;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/currencies")
class ExchangeRateController {
    private final GetExchangeRateAndSaveService getExchangeRateAndSaveService;

    @PostMapping("/get-current-currency-value-command")
    ResponseEntity<ExchangeRate> getExchangeRate(
            @RequestParam String name,
            @RequestParam String currency
    ) {
        final ExchangeRate exchangeRate = getExchangeRateAndSaveService.getExchangeRateFor(currency, name);
        return ResponseEntity.status(HttpStatus.CREATED).body(exchangeRate);
    }

    @GetMapping("/requests")
    ResponseEntity<List<ExchangeRateRequestDto>> getAllRequests() {
        return ResponseEntity.ok(getExchangeRateAndSaveService.getAllExchangeRateRequests());
    }
}
