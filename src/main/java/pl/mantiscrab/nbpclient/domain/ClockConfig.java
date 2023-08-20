package pl.mantiscrab.nbpclient.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Clock;

@Configuration
class ClockConfig {

    @Bean
    @Profile("dev")
    Clock clock() {
        return Clock.systemDefaultZone();
    }
}
