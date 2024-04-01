package com.olivera.loans.api.controllers;

import com.olivera.loans.api.request.CustomerRequest;
import com.olivera.loans.api.response.CustomerLoanResponse;
import com.olivera.loans.services.CustomerLoansService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/costumer-loans")
public class CustomerController {

    @Autowired
    private CustomerLoansService customerLoansService;

    @PostMapping
    public ResponseEntity<CustomerLoanResponse> customerLoans(
           @Valid @RequestBody CustomerRequest customerRequest) {
        CustomerLoanResponse response = customerLoansService
                .determineLoanOptions(customerRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}