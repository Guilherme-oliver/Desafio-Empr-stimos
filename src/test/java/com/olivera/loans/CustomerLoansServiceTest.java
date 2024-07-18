package com.olivera.loans;

import com.olivera.loans.api.request.CustomerRequest;
import com.olivera.loans.api.response.CustomerLoanResponse;
import com.olivera.loans.model.Customer;
import com.olivera.loans.repositories.CustomerRepository;
import com.olivera.loans.services.CustomerLoansService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CustomerLoansServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerLoansService customerLoansService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDetermineLoanOptions_LowIncome() {
        CustomerRequest request = new CustomerRequest(25, 12345678900L, "John Doe", 2500.0, "SP");
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setCpf(request.cpf());
        customer.setAge(request.age());
        customer.setName(request.name());
        customer.setLocation(request.location());
        customer.setIncome(request.income());

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerLoanResponse response = customerLoansService.determineLoanOptions(request);

        assertEquals(request.name(), response.customer());
        assertEquals(2, response.loans().size());
        assertEquals("PERSONAL", response.loans().get(0).type());
        assertEquals(4, response.loans().get(0).interestRate());
        assertEquals("GUARANTEED", response.loans().get(1).type());
        assertEquals(3, response.loans().get(1).interestRate());

        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    public void testDetermineLoanOptions_HighIncome() {
        CustomerRequest request = new CustomerRequest(40, 12345678900L, "Jane Doe", 6000.0, "SP");
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setCpf(request.cpf());
        customer.setAge(request.age());
        customer.setName(request.name());
        customer.setLocation(request.location());
        customer.setIncome(request.income());

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerLoanResponse response = customerLoansService.determineLoanOptions(request);

        assertEquals(request.name(), response.customer());
        assertEquals(1, response.loans().size());
        assertEquals("CONSIGNMENT", response.loans().get(0).type());
        assertEquals(2, response.loans().get(0).interestRate());

        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    public void testDetermineLoanOptions_MediumIncome_SpecialConditions() {
        CustomerRequest request = new CustomerRequest(25, 12345678900L, "Alice Doe", 4000.0, "SP");
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setCpf(request.cpf());
        customer.setAge(request.age());
        customer.setName(request.name());
        customer.setLocation(request.location());
        customer.setIncome(request.income());

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerLoanResponse response = customerLoansService.determineLoanOptions(request);

        assertEquals(request.name(), response.customer());
        assertEquals(2, response.loans().size());
        assertEquals("PERSONAL", response.loans().get(0).type());
        assertEquals(4, response.loans().get(0).interestRate());
        assertEquals("GUARANTEED", response.loans().get(1).type());
        assertEquals(3, response.loans().get(1).interestRate());

        verify(customerRepository, times(1)).save(any(Customer.class));
    }
}