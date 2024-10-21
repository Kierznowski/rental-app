package com.kierznowski.rentalapp.client.controllers;

import com.kierznowski.rentalapp.client.model.Offer;
import com.kierznowski.rentalapp.client.services.OfferService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /*@PostMapping
    public void addOffer(Offer offer) {

        offerService.addOffer(offer);
    */

    @PostMapping
    public ResponseEntity<?> addOffer(@RequestBody Offer offer, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String sessionId = null;
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if("sessionId".equals(cookie.getName())) {
                    sessionId = cookie.getValue();
                    break;
                }
            }
        }
        if(sessionId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        return
    }
}
