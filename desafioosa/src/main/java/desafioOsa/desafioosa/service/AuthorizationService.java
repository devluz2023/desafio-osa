package desafioOsa.desafioosa.service;

import desafioOsa.desafioosa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
        UserDetails user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with login: " + username);
        }
        return user;
    }
}