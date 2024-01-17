package com.optum.bankingapi.dtos;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class FullNameInput {

    private String firstName;

    private String middleName;

    private String lastName;
}
