package com.olivera.loans.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CustomerRequest(
        @NotNull @Positive Integer age,
        @NotNull @Positive Long cpf,
        @NotBlank String name,
        @NotNull @Positive Double income,
        @NotBlank String location) {
}