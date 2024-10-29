package com.kierznowski.rentalApp.controllers;

import com.kierznowski.rentalApp.models.Offer;
import com.kierznowski.rentalApp.utils.APIResponse;
import com.kierznowski.rentalApp.searching.OfferSearchDTO;
import com.kierznowski.rentalApp.searching.OfferSpecificationBuilder;
import com.kierznowski.rentalApp.searching.SearchCriteria;
import com.kierznowski.rentalApp.services.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/searchOffers")
@AllArgsConstructor
public class SearchOffersController {

    private OfferService offerService;

    @PostMapping
    public ResponseEntity<APIResponse> searchOffers(@RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                    @RequestBody OfferSearchDTO offerSearchDTO) {
        APIResponse apiResponse = new APIResponse();
        OfferSpecificationBuilder builder = new OfferSpecificationBuilder();
        List<SearchCriteria> criteriaList = offerSearchDTO.getSearchCriteria();
        if(criteriaList != null){
            criteriaList.forEach(x-> {
                x.setDataOption(offerSearchDTO.getDataOption());
                builder.with(x);
            });
        }

        Pageable page = PageRequest.of(pageNum, pageSize,
                Sort.by("createdAt")
                        .ascending()
                        .and(Sort.by("offerName"))
                        .ascending());


        Page<Offer> offerPage = offerService.findBySearchCriteria(builder.build(), page);
        apiResponse.setData(offerPage.toList());
        apiResponse.setResponseCode(HttpStatus.OK);
        apiResponse.setMessage("Successfully retrieved offer");

        return new ResponseEntity<>(apiResponse, apiResponse.getResponseCode());
    }

    @GetMapping("/offers")
    public ResponseEntity<APIResponse> getAllOffers() {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setData(offerService.findAllOffer());
        apiResponse.setResponseCode(HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, apiResponse.getResponseCode());
    }


}
