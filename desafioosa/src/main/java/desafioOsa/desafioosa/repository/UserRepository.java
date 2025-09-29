package desafioOsa.desafioosa.repository;


import desafioOsa.desafioosa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByLogin(String login);

    boolean existsByCpf(String cpf);

    UserDetails findByLogin(String login);


}