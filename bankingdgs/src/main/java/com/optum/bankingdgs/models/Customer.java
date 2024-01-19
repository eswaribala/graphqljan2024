package com.optum.bankingdgs.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

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
    @Column(name="Gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name="DOB")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dob;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "customer")
    private List<Address> addressList;

}
