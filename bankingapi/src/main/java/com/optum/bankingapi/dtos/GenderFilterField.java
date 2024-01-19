package com.optum.bankingapi.dtos;

import com.optum.bankingapi.models.Gender;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import lombok.Data;

@Data
public class GenderFilterField {
    private String operator;
    private Gender value;
    public Predicate generateCriteria(CriteriaBuilder cb, Path field) {

        switch (operator) {
            case "endsWith": return cb.like(field, "%" + value);
            case "startsWith": return cb.like(field, value + "%");
            case "contains": return cb.like(field, "%" + value + "%");
            case "eq": return cb.equal(field, value);
        }

        return null;
    }
}
