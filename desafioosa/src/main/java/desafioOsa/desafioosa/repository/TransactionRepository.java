package desafioOsa.desafioosa.repository;

import desafioOsa.desafioosa.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
}
