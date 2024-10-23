package com.kierznowski.rentalapp.client.controllers;

import com.kierznowski.rentalapp.client.model.Offer;
import com.kierznowski.rentalapp.client.services.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/bff/offers")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @GetMapping
    public Iterable<Offer> getOffers() {
        return offerService.getAll();
    }

    @PostMapping
    public void addOffer(@RequestBody Offer offer) {
        offerService.addOffer(offer);
    }
}
