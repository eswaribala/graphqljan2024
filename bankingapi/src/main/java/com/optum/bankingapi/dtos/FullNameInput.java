package com.optum.bankingapi.dtos;


import lombok.Data;

@Data
public class FullNameInput {

    private String firstName;

    private String middleName;

    private String lastName;
}
