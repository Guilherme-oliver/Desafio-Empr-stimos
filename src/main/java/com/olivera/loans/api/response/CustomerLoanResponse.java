package com.olivera.loans.api.response;

import com.olivera.loans.api.request.LoanRequest;

import java.util.List;

public record CustomerLoanResponse(String customer, List<LoanRequest> loans) {
}