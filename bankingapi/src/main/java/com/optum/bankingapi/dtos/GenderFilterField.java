package com.optum.bankingapi.dtos;

import com.optum.bankingapi.models.Gender;
import lombok.Data;

@Data
public class GenderFilterField {
    private String operator;
    private Gender value;
}
