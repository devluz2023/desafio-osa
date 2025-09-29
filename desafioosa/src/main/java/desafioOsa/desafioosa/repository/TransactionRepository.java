package desafioOsa.desafioosa.repository;

import desafioOsa.desafioosa.model.Transaction;
import desafioOsa.desafioosa.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);
}
