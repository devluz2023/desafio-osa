package desafioOsa.desafioosa.mapper;

import desafioOsa.desafioosa.dto.UserRegisterDTO;
import desafioOsa.desafioosa.dto.UserResponseDTO;
import desafioOsa.desafioosa.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    public UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
            user.getId(),
            user.getFullName(),
            user.getCpf(),
            user.getLogin()
        );
    }


    public User toEntity(UserRegisterDTO dto) {
        return User.builder()
                .fullName(dto.fullName())
                .cpf(dto.cpf())
                .login(dto.login())
                .password(dto.password()) 
                .build();
    }
}
