package com.kierznowski.rentalapp.client.services;

import com.kierznowski.rentalapp.client.model.Offer;
import com.kierznowski.rentalapp.client.searching.APIResponse;
import com.kierznowski.rentalapp.client.searching.OfferSearchDTO;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

public class RestOfferService implements OfferService {

    private final RestTemplate restTemplate;

    public RestOfferService(String accessToken) {
        this.restTemplate = new RestTemplate();
        if(accessToken != null) {
            this.restTemplate
                    .getInterceptors()
                    .add(getBearerTokenInterceptor(accessToken));
        }
    }

    private ClientHttpRequestInterceptor getBearerTokenInterceptor(String accessToken) {
        return new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                request.getHeaders().add("Authorization", "Bearer " + accessToken);
                return execution.execute(request, body);
            }
        };
    }

    @Override
    public Iterable<Offer> getAll() {
        return Arrays.asList(restTemplate.getForObject(
                "http://localhost:8080/api/offers?recent", Offer[].class));
    }

    @Override
    public Offer addOffer(Offer offer) {
        return restTemplate.postForObject("http://localhost:8080/api/offers", offer, Offer.class);
    }

    @Override
    public ResponseEntity<APIResponse> searchOffers(@RequestBody Map<String, Object> data) {
        OfferSearchDTO convertedBody = RequestConverter.convertToOfferSearchDTO(data);
        return  restTemplate.postForEntity("http://localhost:8080/api/searchOffers", convertedBody,APIResponse.class);
    }

    @Override
    public ResponseEntity<Offer> getOfferById(Long id) {
        return restTemplate.getForEntity("http://localhost:8080/api/offers/" + id, Offer.class);
    }
}
