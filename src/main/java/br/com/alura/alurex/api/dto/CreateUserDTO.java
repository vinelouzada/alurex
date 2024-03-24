package br.com.alura.alurex.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateUserDTO(
        @NotBlank
        String name,
        @NotBlank
        @Size(max = 20)
        @Pattern(regexp = "^[a-z]+$")
        String username,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password
) {
}
