package com.optum.bankingapi.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Account_No")
    private long accountNo;
    @Embedded
    private FullName name;
    private String email;
    private String password;
    private long phoneNo;
}
