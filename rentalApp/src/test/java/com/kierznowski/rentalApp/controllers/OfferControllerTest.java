package com.kierznowski.rentalApp.controllers;

import com.kierznowski.rentalApp.models.Offer;
import com.kierznowski.rentalApp.repositories.OfferRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testing OfferController class")
public class OfferControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private OfferRepository offerRepository;

    private List<Offer> offerList;

    @Test
    @DisplayName("Testing retrieving recent offers")
    void testGetRecentOffers() throws Exception {
        Offer offer1 = new Offer();
        offer1.setId(1L);
        Offer offer2 = new Offer();
        offer2.setId(2L);
        offerList = Arrays.asList(offer1, offer2);
        Page<Offer> mockPage = new PageImpl<>(offerList);

        when(offerRepository.findAll(any(PageRequest.class))).thenReturn(mockPage);

        mockMvc.perform(get("/api/offers").param("recent", ""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));

        ArgumentCaptor<PageRequest> pageRequestCaptor = ArgumentCaptor.forClass(PageRequest.class);
        verify(offerRepository, times(1)).findAll(pageRequestCaptor.capture());
        // checking proper pagination
        PageRequest capturePageRequest = pageRequestCaptor.getValue();
        assertAll("Verify pagination of the recent in offers controller",
                () -> assertEquals(0, capturePageRequest.getPageNumber()),
                () -> assertEquals(12, capturePageRequest.getPageSize()),
                () -> assertEquals(Sort.by("createdAt").descending(), capturePageRequest.getSort()));
    }

    @Test
    @DisplayName("Testing retrieving Offer by Offer id")
    void testGetOfferById() throws Exception {
        Offer offer1 = new Offer();
        offer1.setId(1L);
        when(offerRepository.findById(1L)).thenReturn(Optional.of(offer1));
        when(offerRepository.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/offers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        mockMvc.perform(get("/api/offers/99"))
                .andExpect(status().isNotFound());

        verify(offerRepository, times(1)).findById(1L);
        verify(offerRepository, times(1)).findById(99L);
    }

    @Test
    @DisplayName("Testing creating a new Offer as an registered User")
    void testPostOfferWithRegisteredUser() throws Exception {
        Offer newOffer = new Offer();
        newOffer.setOfferName("New offer title");
        newOffer.setCity("Warsaw");
        newOffer.setRoomsNumber(3);
        newOffer.setFullPrice(BigDecimal.valueOf(1000000));

        User mockUser = new User("test@test.com", "pass",
                true, true, true, true,
                List.of(new SimpleGrantedAuthority("ROLE_USER")));

        when(offerRepository.save(any(Offer.class))).thenAnswer(invocation -> {
            Offer offer = invocation.getArgument(0);
            offer.setId(1L);
            return offer;
        });

        mockMvc.perform(post("/api/offers")
                .contentType("application/json")
                .content("""
                        {
                            "offerName": "New offer title",
                            "city": "Warsaw",
                            "roomsNumber": 3,
                            "fullPrice": 1000000
                        }
                        """)
                .with(user(mockUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.offerName").value("New offer title"))
                .andExpect(jsonPath("$.city").value("Warsaw"))
                .andExpect(jsonPath("$.roomsNumber").value(3))
                .andExpect(jsonPath("$.fullPrice").value(1000000));

        ArgumentCaptor<Offer> offerCaptor = ArgumentCaptor.forClass(Offer.class);
        verify(offerRepository, times(1)).save(offerCaptor.capture());

        Offer capturedOffer = offerCaptor.getValue();
        assertAll("Verify saved offer properties",
                () -> assertEquals("New offer title", capturedOffer.getOfferName()),
                () -> assertEquals("Warsaw", capturedOffer.getCity()),
                () -> assertEquals(3, capturedOffer.getRoomsNumber()),
                () -> assertEquals(BigDecimal.valueOf(1000000), capturedOffer.getFullPrice())
        );
    }

    @Test
    @DisplayName("Testing creating a new Offer as an unauthenticated User")
    void testPostOfferWithAnonymousUser() throws Exception{
        mockMvc.perform(post("/api/offers")
                .contentType("application/json")
                .content("""
                        {
                            "offerName": "New offer title",
                            "city": "Warsaw",
                            "roomsNumber": 3,
                            fullPrice: 1000000
                        }
                        """))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Testing deleting offer as the offer owner")
    @Disabled("Empty test")
    void testDeleteOfferAsOfferOwner() throws Exception{

    }

    @Test
    @DisplayName("Testing deleting offer as a foreign user")
    @Disabled("Empty test")
    void testDeleteOfferAsForeignUser() throws Exception{

    }

    @Test
    @DisplayName("Testing updating offer as the offer owner")
    @Disabled("Empty test")
    void testUpdatingOfferAsOfferOwner() throws Exception{

    }
    @Test
    @DisplayName("Testing updating offer as a foreign user")
    @Disabled("Empty test")
    void testUpdatingOfferAsForeignUser() throws Exception{

    }
}
