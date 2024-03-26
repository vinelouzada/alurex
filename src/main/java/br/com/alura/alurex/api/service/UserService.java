package br.com.alura.alurex.api.service;

import br.com.alura.alurex.api.dto.CreateUserDTO;
import br.com.alura.alurex.api.dto.UserDataDTO;
import br.com.alura.alurex.api.dto.UserDetailsByUsernameDTO;
import br.com.alura.alurex.api.exception.User.DuplicateEmailOrUsernameException;
import br.com.alura.alurex.api.exception.User.UserNotFoundException;
import br.com.alura.alurex.api.model.User;
import br.com.alura.alurex.api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDataDTO create(CreateUserDTO dto){
        boolean existsByEmailOrUsername = repository.existsByEmailOrUsername(dto.email(), dto.username());
        if (existsByEmailOrUsername){
            throw new DuplicateEmailOrUsernameException();
        }

        String passwordBCrypt = passwordEncoder.encode(dto.password());

        User user = new User(dto, passwordBCrypt);
        repository.save(user);

        return new UserDataDTO(user);
    }

    public UserDetailsByUsernameDTO findByUsername(String username){

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

    public UserDetails findByEmail(String email){

        Optional<UserDetails> userDetails = repository.findByEmail(email);

        if (userDetails.isEmpty()){
            throw new UserNotFoundException();
        }

        return userDetails.get();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<UserDetails> userDetails = repository.findByEmail(email);

        if (userDetails.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }

        return userDetails.get();
    }
}
