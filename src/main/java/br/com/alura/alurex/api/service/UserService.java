package br.com.alura.alurex.api.service;

import br.com.alura.alurex.api.dto.CreateUserDTO;
import br.com.alura.alurex.api.dto.UserDetailsByUsernameDTO;
import br.com.alura.alurex.api.exception.User.DuplicateEmailException;
import br.com.alura.alurex.api.exception.User.DuplicateUsernameException;
import br.com.alura.alurex.api.exception.User.UserNotFoundException;
import br.com.alura.alurex.api.model.User;
import br.com.alura.alurex.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Long create(CreateUserDTO dto){
        User user = new User(dto);

        boolean existsByEmail = repository.existsByEmail(user.getEmail());
        boolean existsByUsername = repository.existsByUsername(user.getUsername());

        if (existsByEmail){
            throw new DuplicateEmailException();
        }

        if (existsByUsername){
            throw new DuplicateUsernameException();
        }

        repository.save(user);

        return user.getId();
    }

    public UserDetailsByUsernameDTO findByUsername(String username){

        Optional<UserDetailsByUsernameDTO> dto = repository.findByUsername(username);

        if (dto.isEmpty()){
            throw new UserNotFoundException();
        }

        return dto.get();
    }
}
