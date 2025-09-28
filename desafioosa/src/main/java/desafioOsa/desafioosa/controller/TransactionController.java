package desafioOsa.desafioosa.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import desafioOsa.desafioosa.service.TransactionService;


@RestController
public class TransactionController {
 
    @Autowired
    private TransactionService transactionService;
    
}
