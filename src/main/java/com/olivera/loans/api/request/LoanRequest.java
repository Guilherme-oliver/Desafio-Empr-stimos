package com.olivera.loans.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record LoanRequest(
        @NotBlank String type,
        @NotNull @Positive Integer interestRate) {
}