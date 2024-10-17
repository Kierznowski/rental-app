package com.kierznowski.rentalapp.client.services;

import com.kierznowski.rentalapp.client.model.Offer;

public interface OfferService {
    Iterable<Offer> getAll();
    Offer addOffer(Offer offer);
}
