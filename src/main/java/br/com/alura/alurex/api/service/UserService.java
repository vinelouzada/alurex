package br.com.alura.alurex.api.service;

import br.com.alura.alurex.api.dto.CreateUserDTO;
import br.com.alura.alurex.api.dto.UserDetailsByUsernameDTO;
import br.com.alura.alurex.api.model.User;
import br.com.alura.alurex.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Long create(CreateUserDTO dto){
        User user = new User(dto);
        repository.save(user);

        return user.getId();
    }

    public UserDetailsByUsernameDTO findByUsername(String username){
        return repository.findByUsername(username);
    }
}
