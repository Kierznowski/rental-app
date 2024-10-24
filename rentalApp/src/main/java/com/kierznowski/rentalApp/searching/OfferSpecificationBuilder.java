package com.kierznowski.rentalApp.searching;

import com.kierznowski.rentalApp.models.Offer;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class OfferSpecificationBuilder {
    private final List<SearchCriteria> params;

    public OfferSpecificationBuilder(){
        this.params = new ArrayList<>();
    }

    public final OfferSpecificationBuilder with(String key, String operation, Object value){
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public final OfferSpecificationBuilder with(SearchCriteria searchCriteria){
        params.add(searchCriteria);
        return this;
    }

    public Specification<Offer> build(){
        if(params.size() == 0){
            return null;
        }

        Specification<Offer> result = new OfferSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++){
            SearchCriteria criteria = params.get(i);
            result =  SearchOperation.getDataOption(criteria.getDataOption()) == SearchOperation.ALL ?
                    Specification.where(result).and(new OfferSpecification(criteria)) :
                    Specification.where(result).or(new OfferSpecification(criteria));
        }
        return result;
    }
}
