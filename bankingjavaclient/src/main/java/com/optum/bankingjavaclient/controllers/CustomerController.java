package com.optum.bankingjavaclient.controllers;

import com.optum.bankingjavaclient.services.ConsumeBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ConsumeBankService consumeBankService;


    @GetMapping({"/v1.0/"})
    public ResponseEntity<String> fetchCustomers(){
        return ResponseEntity.status(HttpStatus.OK).body(this.consumeBankService.getAllCustomers());
    }

}
