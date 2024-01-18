package com.optum.bankingapi.dtos;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import lombok.Data;



@Data
public class FilterField {

    private String operator;
    private String value;

    public Predicate generateCriteria(CriteriaBuilder cb, Path field) {
        try {
            int intValue=Integer.parseInt(value);
            switch(operator){
                case "lt":
                      return cb.lt(field,intValue);


                case "le":

                    return cb.le(field,intValue);


                case "gt":
                    return cb.gt(field,intValue);


                case "ge":
                    return cb.ge(field,intValue);


                case "eq":
                    return cb.equal(field,intValue);


                case "ne":
                    return cb.notEqual(field,intValue);

            }


        } catch (NumberFormatException e) {
            switch (operator) {
                case "endsWith": return cb.like(field, "%" + value);
                case "startsWith": return cb.like(field, value + "%");
                case "contains": return cb.like(field, "%" + value + "%");
                case "eq": return cb.equal(field, value);
            }


        }

    return null;
    }


}
