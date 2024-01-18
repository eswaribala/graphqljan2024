package com.optum.bankingdgs.dtos;


import com.optum.bankingdgs.models.Gender;
import lombok.Data;

import java.time.LocalDate;



@Data
public class CustomerInput {


    private FullNameInput name;
    private String email;
    private String password;
    private long phoneNo;
    private Gender gender;
    private LocalDate dob;


}
