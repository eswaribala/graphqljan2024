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

            if (operator.equals("eq")){
                if(value!=null)
                    return cb.equal(field,value);
                else
                    return null;
            }else
               return null;
    }
}
