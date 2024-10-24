package com.kierznowski.rentalApp.searching;

import com.kierznowski.rentalApp.models.Offer;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class OfferSpecification implements Specification<Offer> {
    private final SearchCriteria searchCriteria;

    public OfferSpecification(final SearchCriteria searchCriteria) {
        super();
        this.searchCriteria = searchCriteria;
    }


    @Override
    public Predicate toPredicate(Root<Offer> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        String strToSearch = searchCriteria.getValue().toString().toLowerCase();

        switch(Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))) {
            case CONTAINS:
                return builder.like(builder.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
            case DOES_NOT_CONTAIN:
                return builder.notLike(builder.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
            case BEGINS_WITH:
                return builder.like(builder.lower(root.get(searchCriteria.getFilterKey())), strToSearch + "%");
            case DOES_NOT_BEGIN_WITH:
                return builder.notLike(builder.lower(root.get(searchCriteria.getFilterKey())), strToSearch + "%");
            case ENDS_WITH:
                return builder.like(builder.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch);
            case DOES_NOT_END_WITH:
                return builder.notLike(builder.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch);
            case EQUAL:
                return builder.equal(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue());
            case NOT_EQUAL:
                return builder.notEqual(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue());
            case NUL:
                return builder.isNull(root.get(searchCriteria.getFilterKey()));
            case NOT_NULL:
                return builder.isNotNull(root.get(searchCriteria.getFilterKey()));
            case GREATER_THAN:
                return builder.greaterThan(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case GREATER_THAN_EQUAL:
                return builder.greaterThanOrEqualTo(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case LESS_THAN:
                return builder.lessThan(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case LESS_THAN_EQUAL:
                return builder.lessThanOrEqualTo(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

        }
        return null;
    }
}
