package desafioOsa.desafioosa.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import desafioOsa.desafioosa.service.TransactionService;

import java.math.BigDecimal;


@RestController
@RequestMapping("/transactions")  
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestParam Long userId, @RequestParam BigDecimal amount) {
        try {
            transactionService.deposit(userId, amount);
            return ResponseEntity.ok("Deposit successful");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam Long userId, @RequestParam BigDecimal amount) {
        try {
            transactionService.withdraw(userId, amount);
            return ResponseEntity.ok("Withdrawal successful");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/payBill")
    public ResponseEntity<String> payBill(@RequestParam Long userId, @RequestParam BigDecimal amount) {
        try {
            transactionService.payBill(userId, amount);
            return ResponseEntity.ok("Payment successful");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/history")
    public ResponseEntity<?> getTransactionHistory(@RequestParam Long userId) {
        try {
            return ResponseEntity.ok(transactionService.getTransactionHistory(userId)); 
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/balance")
    public ResponseEntity<?> getBalance(@RequestParam Long userId) {
        try {
             return ResponseEntity.ok(transactionService.getBalance(userId)); 
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}