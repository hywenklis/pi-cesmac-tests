package com.projeto.integrador.cesmac.pi.controllers.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record StudentRequest(
    @NotBlank(message = "Name is required")
    String name,

    @NotBlank(message = "Registration number is required")
    String registrationNumber
) {}
