package com.kierznowski.rentalapp.client.services;

import com.kierznowski.rentalapp.client.model.Offer;
import com.kierznowski.rentalapp.client.searching.APIResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface OfferService {
    Iterable<Offer> getAll();
    Offer addOffer(Offer offer);

    ResponseEntity<APIResponse> searchOffers(Map<String, Object> json);

    ResponseEntity<Offer> getOfferById(Long id);
}
