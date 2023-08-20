package pl.mantiscrab.nbpclient.domain;

import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

interface ExchangeRateRequestRepository extends ListCrudRepository<ExchangeRateRequest, UUID> {
}
