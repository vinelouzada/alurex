package br.com.alura.alurex.api.model;

import br.com.alura.alurex.api.dto.CreateUserDTO;
import br.com.alura.alurex.api.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Table(name = "users")
@Entity(name = "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String role;
    private LocalDate createdAt;

    @OneToMany(mappedBy = "user")
    private List<Enrollment> enrollments;

    public User(CreateUserDTO dto){
        this.name = dto.name();
        this.username = dto.username();
        this.email = dto.email();
        this.password = dto.password();
        this.role = Role.STUDENT.name();
        this.createdAt = LocalDate.now();
    }
}
