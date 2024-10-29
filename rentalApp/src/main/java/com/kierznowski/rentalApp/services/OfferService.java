package com.kierznowski.rentalApp.services;

import com.kierznowski.rentalApp.models.Offer;
import com.kierznowski.rentalApp.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    public List<Offer> findAllOffer(){
        return offerRepository.findAll();
    }

    public Page<Offer> findBySearchCriteria
            (Specification<Offer> spec, Pageable page){
        return offerRepository.findAll(spec, page);
    }

    public void addImageToOffer(Long offerId, Long imageId) {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        offer.getImageIds().add(imageId);
        offerRepository.save(offer);
    }

}
