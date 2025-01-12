package com.kierznowski.rentalApp;

import com.kierznowski.rentalApp.models.Offer;
import com.kierznowski.rentalApp.repositories.OfferRepository;
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
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testing RentalApp resource server Rest API")
public class RestApiTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private OfferRepository offerRepository;

    @Test
    @DisplayName("Testing findAll() method of OfferController")
    void testGetRecentOffers() throws Exception {

        Offer offer1 = new Offer();
        offer1.setId(1L);
        Offer offer2 = new Offer();
        offer2.setId(2L);
        List<Offer> offerList = Arrays.asList(offer1, offer2);
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
}
