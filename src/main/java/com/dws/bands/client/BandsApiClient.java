package com.dws.bands.client;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.dws.bands.exception.ExternalServiceException;
import com.dws.bands.model.dto.BandDto;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BandsApiClient {

    private final WebClient bandsWebClient;

    public List<BandDto> fetchBands() {
        return bandsWebClient.get()
                .uri("/bands")
                .retrieve()
                .onStatus(HttpStatusCode::isError,
                        response -> Mono.error(new ExternalServiceException("Error calling Bands API")))
                .bodyToFlux(BandDto.class)
                .collectList()
                .block();
    }
    
    public BandDto fetchBand(String id) {
        return bandsWebClient.get()
                .uri("/bands/" + id)
                .retrieve()
                .onStatus(HttpStatusCode::isError,
                        response -> Mono.error(new ExternalServiceException("Error calling Bands API")))
                .bodyToFlux(BandDto.class)
                .blockFirst();
    }
}
