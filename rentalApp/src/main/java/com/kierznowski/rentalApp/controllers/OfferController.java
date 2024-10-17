package com.kierznowski.rentalApp.controllers;

import com.kierznowski.rentalApp.models.Offer;
import com.kierznowski.rentalApp.repositories.OfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/api/offers", produces = "application/json")
@CrossOrigin(origins="http://localhost:9090")
@AllArgsConstructor
public class OfferController {

    private OfferRepository offerRepository;

    @GetMapping(params="recent")
    public Iterable<Offer> getOffers() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return offerRepository.findAll(page).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offer> offerById(@PathVariable("id") Long id) {
        Optional<Offer> optionalOffer = offerRepository.findById(id);
        if(optionalOffer.isPresent()) {
            return new ResponseEntity<>(optionalOffer.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Offer postOffer(@RequestBody Offer offer) {
        return offerRepository.save(offer);
    }

    @PutMapping(path="/{offerId}", consumes = "application/json")
    public Offer putOffer(@PathVariable("offerId") Long id, @RequestBody Offer offer) {
        offer.setId(id);
        return offerRepository.save(offer);
    }

    @PatchMapping(path="/{offerId}", consumes = "application/json")
    public Offer patchOffer(@PathVariable("offerId") Long id, @RequestBody Offer patch) {
        Offer offer = offerRepository.findById(id).get();
        if(patch.getOfferName() != null) {
            offer.setOfferName(patch.getOfferName());
        }
        if(patch.getCity() != null) {
            offer.setCity(patch.getCity());
        }
        if(patch.getDistrict() != null) {
            offer.setDistrict(patch.getDistrict());
        }
        if(patch.getStreet() != null) {
            offer.setStreet(patch.getStreet());
        }
        if(patch.getZip() != null) {
            offer.setZip(patch.getZip());
        }
        if(patch.getBuildingYear() != 0) {
            offer.setBuildingYear(patch.getBuildingYear());
        }
        if(patch.getFullPrice() != null) {
            offer.setFullPrice(patch.getFullPrice());
        }
        if(patch.getBasePrice() != null) {
            offer.setBasePrice(patch.getBasePrice());
        }
        if(patch.getAdditionalPrice() != null) {
            offer.setAdditionalPrice(patch.getAdditionalPrice());
        }
        if(patch.getArea() != 0) {
            offer.setArea(patch.getArea());
        }
        if(patch.getRoomsNumber() != 0) {
            offer.setRoomsNumber(patch.getRoomsNumber());
        }
        if(patch.getEstateLevel() != 0) {
            offer.setEstateLevel(patch.getEstateLevel());
        }
        if(patch.isGarage() != offer.isGarage()) {
            offer.setGarage(patch.isGarage());
        }
        if(patch.isAnnexKitchen() != offer.isAnnexKitchen()) {
            offer.setAnnexKitchen(patch.isAnnexKitchen());
        }
        if(patch.isElevator() != offer.isElevator()) {
            offer.setElevator(patch.isElevator());
        }
        if(patch.isAnimals() != offer.isAnimals()) {
            offer.setAnimals(patch.isAnimals());
        }
        return offerRepository.save(offer);
    }

    @DeleteMapping("/{offerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOffer(@PathVariable("offerId") Long id) {
        try {
            offerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){}
    }


}
