package com.olivera.loans.services;

import com.olivera.loans.api.request.CustomerRequest;
import com.olivera.loans.api.request.LoanRequest;
import com.olivera.loans.api.response.CustomerLoanResponse;
import com.olivera.loans.model.Customer;
import com.olivera.loans.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerLoansService {

    private CustomerRepository customerRepository;

    public CustomerLoansService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerLoanResponse determineLoanOptions(CustomerRequest customerRequest) {

        List<LoanRequest> loans = new ArrayList<>();
        if (customerRequest.income() <= 3000) {
            loans.add(new LoanRequest("PERSONAL", 4));
        } else if (customerRequest.income() <= 4999 && customerRequest.age() < 30 && "SP".equals(customerRequest.location())) {
            loans.add(new LoanRequest("PERSONAL", 4));
        }

        if (customerRequest.income() <= 3000) {
            loans.add(new LoanRequest("GUARANTEED", 3));
        } else if (customerRequest.income() <= 4999 && customerRequest.age() < 30 && "SP".equals(customerRequest.location())) {
            loans.add(new LoanRequest("GUARANTEED", 3));
        }

        if (customerRequest.income() >= 5000) {
            loans.add(new LoanRequest("CONSIGNMENT", 2));
        }

        Customer customer = new Customer();
        customer.setCpf(customerRequest.cpf());
        customer.setAge(customerRequest.age());
        customer.setName(customerRequest.name());
        customer.setLocation(customerRequest.location());
        customer.setIncome(customerRequest.income());
        customer = customerRepository.save(customer);

        return new CustomerLoanResponse(customerRequest.name(), loans);
    }
}