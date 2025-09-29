package desafioOsa.desafioosa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO(
    
    @NotBlank(message = "O nome completo não pode estar em branco")
    String fullName,
    
    @NotBlank(message = "O CPF não pode estar em branco")
    @Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos")
    String cpf,
    
    @NotBlank(message = "O login não pode estar em branco")
    String login,
    
    @NotBlank(message = "A senha não pode estar em branco")
    String password
) {}
 
