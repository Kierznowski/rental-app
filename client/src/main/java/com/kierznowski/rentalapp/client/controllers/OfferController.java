package com.kierznowski.rentalapp.client.controllers;

import com.kierznowski.rentalapp.client.model.Offer;
import com.kierznowski.rentalapp.client.searching.APIResponse;
import com.kierznowski.rentalapp.client.services.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;

    @GetMapping
    Iterable<Offer> getOffers() {
        return offerService.getAll();
    }


    @GetMapping("/{id}")
    ResponseEntity<Offer> getOfferById(@PathVariable("id") Long id) {
        return offerService.getOfferById(id);
    }

    @PostMapping
    Long addOffer(@RequestBody Offer offer) throws Exception {
        Offer addedOffer = offerService.addOffer(offer);
        return addedOffer.getId();
    }

    @PostMapping("/searched-offers")
    Iterable<Offer> searchOffers(@RequestBody Map<String, Object> params) {
        ResponseEntity<APIResponse> response = offerService.searchOffers(params);
        return (Iterable<Offer>) response.getBody().getData();
    }
}
