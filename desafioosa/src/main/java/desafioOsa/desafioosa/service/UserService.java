package desafioOsa.desafioosa.service;

import desafioOsa.desafioosa.dto.UserRegisterDTO;
import desafioOsa.desafioosa.dto.UserResponseDTO;
import desafioOsa.desafioosa.mapper.UserMapper;
import desafioOsa.desafioosa.model.User;
import desafioOsa.desafioosa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

   @Autowired
   private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper; 

    public UserResponseDTO registerUser(UserRegisterDTO userDTO) {

        User user = userMapper.toEntity(userDTO);

        if (!isValidCPF(user.getCpf())) {
            throw new IllegalArgumentException("Invalid CPF format.");
        }

        if (userRepository.existsByLogin(user.getLogin())) {
            throw new DuplicateKeyException("Login already exists.");
        }
        if (userRepository.existsByCpf(user.getCpf())) {
            throw new DuplicateKeyException("CPF already exists.");
        }


       String encodedPassword = passwordEncoder.encode(user.getPassword());
       user.setPassword(encodedPassword);


        User userSaved =  userRepository.save(user);
        return userMapper.toResponseDTO(userSaved);
    }


    private boolean isValidCPF(String cpf) {

        return cpf != null && cpf.length() == 11;
    }


    
}
