package br.com.alura.alurex.api.service;

import br.com.alura.alurex.api.dto.CreateUserDTO;
import br.com.alura.alurex.api.dto.UserDetailsByUsernameDTO;
import br.com.alura.alurex.api.exception.User.DuplicateEmailException;
import br.com.alura.alurex.api.exception.User.DuplicateUsernameException;
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

        //add or
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

    public UserDetailsByUsernameDTO findByUsernameReturnDTO(String username){

        Optional<UserDetailsByUsernameDTO> dto = repository.findByUsernameReturnDTO(username);

        if (dto.isEmpty()){
            throw new UserNotFoundException();
        }

        return dto.get();
    }

    public User findByUsername(String username){
        Optional<User> user = repository.findUserByUsernameReturnModel(username);

        if (user.isEmpty()){
            throw new UserNotFoundException();
        }

        return user.get();
    }

    public User findById(Long id){
        try {
            return repository.getReferenceById(id);
        }catch (EntityNotFoundException ex){
            throw new UserNotFoundException();
        }
    }
}
