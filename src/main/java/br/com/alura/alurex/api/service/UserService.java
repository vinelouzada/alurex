package br.com.alura.alurex.api.service;

import br.com.alura.alurex.api.dto.CreateUserDTO;
import br.com.alura.alurex.api.dto.UserDetailsByUsernameDTO;
import br.com.alura.alurex.api.exception.User.DuplicateEmailOrUsernameException;
import br.com.alura.alurex.api.exception.User.UserNotFoundException;
import br.com.alura.alurex.api.model.User;
import br.com.alura.alurex.api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Long create(CreateUserDTO dto){
        User user = new User(dto);

        boolean existsByEmailOrUsername = repository.existsByEmailOrUsername(user.getEmail(), user.getUsername());

        if (existsByEmailOrUsername){
            throw new DuplicateEmailOrUsernameException();
        }

        repository.save(user);

        return user.getId();
    }

    public UserDetailsByUsernameDTO findByUsernameReturnDTO(String username){

        Optional<UserDetailsByUsernameDTO> dto = repository.findByUsernameReturnDTO(username);

        if (dto.isEmpty()){
            throw new UserNotFoundException();
        }

        return dto.get();
    }

    public User findById(Long id){
        try {
            return repository.getReferenceById(id);
        }catch (EntityNotFoundException ex){
            throw new UserNotFoundException();
        }
    }
}
