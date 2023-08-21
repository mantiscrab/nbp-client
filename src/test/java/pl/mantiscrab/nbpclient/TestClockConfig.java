package pl.mantiscrab.nbpclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@Configuration
class TestClockConfig {

    @Bean
    @Profile("test")
    Clock clock() {
        return Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }
}
