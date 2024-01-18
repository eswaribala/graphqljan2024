package com.optum.bankingapi.dtos;

import com.optum.bankingapi.models.Customer;

import lombok.Data;

@Data
public class AddressInput {


    private String doorNo;

    private  String streetName;

    private String city;

    private long pincode;

}
