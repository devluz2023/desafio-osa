package desafioOsa.desafioosa.controller;
import desafioOsa.desafioosa.dto.UserRegisterDTO;
import desafioOsa.desafioosa.dto.UserResponseDTO;
import desafioOsa.desafioosa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import desafioOsa.desafioosa.service.UserService;
import org.springframework.dao.DuplicateKeyException;


@RestController
public class UserController {
    
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterDTO user) {
        try {
            UserResponseDTO registeredUser = userService.registerUser(user);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (DuplicateKeyException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Registration failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
