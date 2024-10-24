package com.kierznowski.rentalapp.client.controllers;

import com.kierznowski.rentalapp.client.model.Offer;
import com.kierznowski.rentalapp.client.services.OfferService;
import com.kierznowski.rentalapp.client.searching.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/bff/search")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class SearchController {

    private final OfferService offerService;

    @PostMapping
    public Iterable<Offer> searchOffers(@RequestBody Map<String, Object> params) {
        ResponseEntity<APIResponse> response = offerService.searchOffers(params);
        return (Iterable<Offer>) response.getBody().getData();
    }

}
