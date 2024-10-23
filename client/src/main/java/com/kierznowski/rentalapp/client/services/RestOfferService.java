package com.kierznowski.rentalapp.client.services;

import com.kierznowski.rentalapp.client.model.Offer;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

public class RestOfferService implements OfferService {

    private final RestTemplate restTemplate;
    //public RestOfferService() {};

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
}
