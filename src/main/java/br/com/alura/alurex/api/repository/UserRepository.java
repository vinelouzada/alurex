package br.com.alura.alurex.api.repository;

import br.com.alura.alurex.api.dto.UserDetailsByUsernameDTO;
import br.com.alura.alurex.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT new br.com.alura.alurex.api.dto.UserDetailsByUsernameDTO(user.name, user.email, user.role) FROM User user WHERE user.username = :username")
    UserDetailsByUsernameDTO findByUsername(String username);
}