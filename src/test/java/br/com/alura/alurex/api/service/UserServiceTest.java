package br.com.alura.alurex.api.service;

import br.com.alura.alurex.api.dto.CreateUserDTO;
import br.com.alura.alurex.api.exception.User.DuplicateEmailOrUsernameException;
import br.com.alura.alurex.api.model.Course;
import br.com.alura.alurex.api.model.User;
import br.com.alura.alurex.api.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Test
    public void mustBeCreateAUser(){
        CreateUserDTO userDTO = new CreateUserDTO("Vinicius Louzada",
                "vinelouzada", "vinelouzada@gmail.com",
                "123");

        given(userRepository.existsByEmailOrUsername(userDTO.email(),userDTO.username())).willReturn(false);
        given(passwordEncoder.encode(userDTO.password())).willReturn("$2a$10$xYDAmLQiuBIZm5uODh5SKOEYaWtxiMllcBQAHPsgRoIU8gjQG/dpe");

        userService.create(userDTO);

        then(userRepository).should().save(userArgumentCaptor.capture());

        User userCaptor = userArgumentCaptor.getValue();
        Assertions.assertEquals("Vinicius Louzada",userCaptor.getName());
        Assertions.assertEquals("vinelouzada", userCaptor.getUsername());
        Assertions.assertEquals("vinelouzada@gmail.com", userCaptor.getEmail());
        Assertions.assertEquals("$2a$10$xYDAmLQiuBIZm5uODh5SKOEYaWtxiMllcBQAHPsgRoIU8gjQG/dpe", userCaptor.getPassword());
    }

    @Test
    public void mustThrowExceptionWhenUsernameOrEmailAlreadyExists(){
        CreateUserDTO userDTO = new CreateUserDTO("Vinicius Louzada",
                "vinelouzada", "vinelouzada@gmail.com",
                "123");

        given(userRepository.existsByEmailOrUsername(userDTO.email(),userDTO.username())).willReturn(true);

        Assertions.assertThrows(DuplicateEmailOrUsernameException.class, () -> userService.create(userDTO));
    }

}