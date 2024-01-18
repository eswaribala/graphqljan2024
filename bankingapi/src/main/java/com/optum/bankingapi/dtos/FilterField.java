package com.optum.bankingapi.dtos;

import lombok.Data;

@Data
public class FilterField {

    private String operator;
    private String value;
}
