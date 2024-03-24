package br.com.alura.alurex.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDetailsByUsernameDTO {
    private String name;
    private String username;
    private String rule;
}
