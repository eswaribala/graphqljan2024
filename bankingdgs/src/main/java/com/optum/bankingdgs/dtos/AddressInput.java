package com.optum.bankingdgs.dtos;

import lombok.Data;

@Data
public class AddressInput {


    private String doorNo;

    private  String streetName;

    private String city;

    private long pincode;

}
