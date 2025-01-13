package com.kierznowski.rentalApp.services;


import com.kierznowski.rentalApp.models.Offer;
import com.kierznowski.rentalApp.repositories.OfferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OfferServiceTest {

    @InjectMocks
    private OfferService offerService;

    @Mock
    private OfferRepository offerRepository;

    private Offer offer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        offer = new Offer();
        offer.setId(1L);
        offer.setOfferName("Test offer name");
        offer.setCity("Warsaw");
        offer.setFullPrice(BigDecimal.valueOf(1000000));
    }

    @Test
    void testFindAllOffer() {
        List<Offer> offers = List.of(offer);
        when(offerRepository.findAll()).thenReturn(offers);

        List<Offer> result = offerService.findAllOffer();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test offer name", result.get(0).getOfferName());
        verify(offerRepository, times(1)).findAll();
    }

    @Test
    void testFindBySearchCriteria() {
        Pageable pageable = mock(Pageable.class);
        Specification<Offer> spec = mock(Specification.class);
        Page<Offer> offerPage = mock(Page.class);
        when(offerRepository.findAll(spec, pageable)).thenReturn(offerPage);

        Page<Offer> result = offerService.findBySearchCriteria(spec, pageable);

        assertNotNull(result);
        verify(offerRepository, times(1)).findAll(spec, pageable);
    }

    @Test
    void testAddImageToOffer() {
        Long offerId = 1L;
        Long imageId = 99L;
        when(offerRepository.findById(offerId)).thenReturn(Optional.of(offer));

        offerService.addImageToOffer(offerId, imageId);

        assertTrue(offer.getImageIds().contains(imageId));
        verify(offerRepository, times(1)).save(offer);
    }

    @Test
    void testAddImageToOfferNotFound() {
        Long offerId = 1L;
        Long imageId = 99L;
        when(offerRepository.findById(offerId)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            offerService.addImageToOffer(offerId, imageId);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(offerRepository, times(0)).save(any());
    }


}
