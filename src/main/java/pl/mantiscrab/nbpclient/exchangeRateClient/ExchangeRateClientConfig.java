package pl.mantiscrab.nbpclient.exchangeRateClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;
import pl.mantiscrab.nbpclient.domain.ExchangeRateClient;

@Configuration
class ExchangeRateClientConfig {
    @Bean
    @Profile("dev")
    ExchangeRateClient exchangeRateClient(RestTemplate restTemplate) {
        return new ExchangeRateClientImpl(restTemplate);
    }
}
