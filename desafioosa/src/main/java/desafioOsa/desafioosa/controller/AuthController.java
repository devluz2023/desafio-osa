package desafioOsa.desafioosa.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import desafioOsa.desafioosa.service.AuthService;


@RestController
public class AuthController {
    
    @Autowired
    private AuthService authService;
}
