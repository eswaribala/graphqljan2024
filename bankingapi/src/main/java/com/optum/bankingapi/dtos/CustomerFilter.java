package com.optum.bankingapi.dtos;

import lombok.Data;

@Data
public class CustomerFilter {

    private FilterField accountNo;
    private GenderFilterField gender;
}
