package br.com.alura.alurex.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateUserDTO(
        @NotBlank(message = "Name cannot be blank")
        String name,
        @NotBlank(message = "Username cannot be blank")
        @Size(max = 20)
        @Pattern(regexp = "^[a-z]+$", message = "Username must contain only lowercase letters")
        String username,
        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Invalid email format")
        String email,
        @NotBlank(message = "Password cannot be blank")
        String password
) {
}
