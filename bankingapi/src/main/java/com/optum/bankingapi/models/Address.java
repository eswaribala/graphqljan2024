package com.optum.bankingapi.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name="Address")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Address_Id")
    private long addressId;
    @Column(name="Door_No",nullable = false,length = 6)
    private String doorNo;
    @Column(name="Street_Name",nullable = false,length = 100)
    private  String streetName;
    @Column(name="City",nullable = false,length = 100)
    private String city;
    @Column(name="Pin_Code")
    private long pincode;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(foreignKey = @ForeignKey(name = "Account_No"), name = "Account_No")
    private Customer customer;
}
