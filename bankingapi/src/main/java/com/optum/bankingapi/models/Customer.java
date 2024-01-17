package com.optum.bankingapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Customer")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Account_No")
    private long accountNo;
    @Embedded
    private FullName name;
    @Column(name="Email",nullable = false, length = 150)
    private String email;
    @Column(name="Password", nullable = false, length = 10)
    private String password;
    @Column(name="Phone_No")
    private long phoneNo;
}
