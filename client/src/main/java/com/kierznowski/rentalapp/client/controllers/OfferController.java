package com.kierznowski.rentalapp.client.controllers;

import com.kierznowski.rentalapp.client.model.Offer;
import com.kierznowski.rentalapp.client.services.OfferService;
import com.kierznowski.rentalapp.client.services.RestOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/bff/offers")
@RequiredArgsConstructor
public class OfferController {

    @Autowired
    private OfferService offerService;

    @GetMapping
    public Iterable<Offer> getOffers() {
        return offerService.getAll();
    }
}
