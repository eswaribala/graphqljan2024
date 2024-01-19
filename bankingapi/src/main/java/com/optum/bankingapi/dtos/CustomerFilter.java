package com.optum.bankingapi.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CustomerFilter {

    private FilterField accountNo;
    private FilterField email;
    private GenderFilterField gender;
    private List<CustomerFilter> and;
    private List<CustomerFilter> or;
}
