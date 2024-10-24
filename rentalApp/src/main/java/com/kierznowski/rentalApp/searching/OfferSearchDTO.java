package com.kierznowski.rentalApp.searching;

import com.kierznowski.rentalApp.searching.SearchCriteria;
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
