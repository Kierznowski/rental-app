package com.kierznowski.rentalapp.client.controllers;

import com.kierznowski.rentalapp.client.model.Offer;
import com.kierznowski.rentalapp.client.services.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/bff/offers")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class OfferController {

    private final OfferService offerService;

    @GetMapping
    public Iterable<Offer> getOffers() {
        return offerService.getAll();
    }

    @PostMapping
    public Long addOffer(@RequestBody Offer offer) throws Exception {
        Offer addedOffer = offerService.addOffer(offer);
        return addedOffer.getId();
    }
}
