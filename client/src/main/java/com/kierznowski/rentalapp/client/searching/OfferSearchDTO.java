package com.kierznowski.rentalapp.client.searching;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferSearchDTO {

    private List<SearchCriteria> searchCriteria;
    private String dataOption;
}