package com.optum.bankingapi.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Address")
@Data
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
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(foreignKey = @ForeignKey(name = "Account_No"), name = "Account_No")
    private Customer customer;
}
