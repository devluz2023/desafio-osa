package desafioOsa.desafioosa.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import desafioOsa.desafioosa.service.UserService;


@RestController
public class UserController {
    
    @Autowired
    private UserService userService;
}
