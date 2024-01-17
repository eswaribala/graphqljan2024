package com.optum.bankingapi.dtos;

import com.optum.bankingapi.models.FullName;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class CustomerInput {


    private FullNameInput name;
    private String email;
    private String password;
    private long phoneNo;


}
