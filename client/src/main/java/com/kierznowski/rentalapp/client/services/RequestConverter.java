package com.kierznowski.rentalapp.client.services;

import com.kierznowski.rentalapp.client.searching.OfferSearchDTO;
import com.kierznowski.rentalapp.client.searching.SearchCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RequestConverter {

    public static OfferSearchDTO convertToOfferSearchDTO(Map<String, Object> data) {
        List<SearchCriteria> searchCriteria = new ArrayList<>();

        for(Map.Entry<String, Object> criterium : data.entrySet()) {
            if(criterium.getValue() != null && !criterium.getValue().equals("") && !criterium.getValue().equals(false)) {
                searchCriteria.add(new SearchCriteria(
                        RequestConverter.variableRefiner(criterium.getKey()),
                        RequestConverter.getOperation(criterium.getKey()),
                        criterium.getValue()));
            }
        }
        return new OfferSearchDTO(searchCriteria, "all");
    }

    public static String getOperation(String key) {
        if(key.contains("min")) {
            return "ge";
        } else if(key.contains("max")) {
            return "le";
        } else {
            return "eq";
        }
    }

    public static String variableRefiner(String variable) {
        String newVariable =  variable.replace("min", "")
                .replace("max", "")
                .replace("Price", "fullPrice");
        return newVariable.substring(0, 1).toLowerCase() + newVariable.substring(1);
    }
}
