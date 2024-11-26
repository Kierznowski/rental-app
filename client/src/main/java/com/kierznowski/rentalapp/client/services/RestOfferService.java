package com.kierznowski.rentalapp.client.services;

import com.kierznowski.rentalapp.client.model.Offer;
import com.kierznowski.rentalapp.client.searching.APIResponse;
import com.kierznowski.rentalapp.client.searching.OfferSearchDTO;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${resource.url.offer.recent}")
    String recentOffersUrl;
    @Value("${resource.url.offer.add}")
    String addOfferUrl;
    @Value("${resource.url.offer.search}")
    String searchOffersUrl;
    @Value("${resource.url.offer.getOffer}")
    String getOfferUrl;


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
                recentOffersUrl, Offer[].class));
    }

    @Override
    public Offer addOffer(Offer offer) {
        return restTemplate.postForObject(addOfferUrl, offer, Offer.class);
    }

    @Override
    public ResponseEntity<APIResponse> searchOffers(@RequestBody Map<String, Object> data) {
        OfferSearchDTO convertedBody = RequestConverter.convertToOfferSearchDTO(data);
        return  restTemplate.postForEntity(searchOffersUrl, convertedBody,APIResponse.class);
    }

    @Override
    public ResponseEntity<Offer> getOfferById(Long id) {
        return restTemplate.getForEntity(getOfferUrl + id, Offer.class);
    }
}
