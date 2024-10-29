package com.kierznowski.rentalApp.services;

import com.kierznowski.rentalApp.models.Offer;
import com.kierznowski.rentalApp.repositories.OfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class OfferService {

    private OfferRepository offerRepository;

    public List<Offer> findAllOffer(){
        return offerRepository.findAll();
    }

    public Page<Offer> findBySearchCriteria(Specification<Offer> spec, Pageable page){
        return offerRepository.findAll(spec, page);
    }

    public void addImageToOffer(Long offerId, Long imageId) {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        offer.getImageIds().add(imageId);
        offerRepository.save(offer);
    }

}
