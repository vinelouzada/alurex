package br.com.alura.alurex.api.dto;

import br.com.alura.alurex.api.model.User;

public record UserDataDTO(Long id, String name, String username, String email) {
    public UserDataDTO(User user){
        this(user.getId(),user.getName(), user.getUsername(), user.getEmail());
    }
}
