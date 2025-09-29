//package desafioOsa.desafioosa.service;
//
//import desafioOsa.desafioosa.model.Transaction;
//import desafioOsa.desafioosa.model.User;
//import desafioOsa.desafioosa.repository.TransactionRepository;
//import desafioOsa.desafioosa.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//@Service
//public class TransactionService {
//
//    @Autowired
//    private TransactionRepository transactionRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Cacheable(value = "balances", key = "#userId")
//    public BigDecimal getBalance(Long userId) {
//        System.out.println("Buscando saldo do banco de dados para o usuário " + userId);
//        return null;
//    }
//
//    @Cacheable(value = "transactionHistories", key = "#userId")
//    public List<Transaction> getTransactionHistory(Long userId) {
//        System.out.println("Buscando histórico de transações do banco de dados para o usuário " + userId);
//
//        return null;
//    }
//
//    @CacheEvict(value = "balances", key = "#userId")
//    public void deposit(Long userId, BigDecimal amount) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
//            throw new IllegalArgumentException("Deposit amount must be positive");
//        }
//
//        BigDecimal currentBalance = getBalance(userId);
//        BigDecimal newBalance = currentBalance.add(amount);
//
//        //... (atualizar a entidade User e salvar no banco) ...
//
//        //Criar a transação
//        Transaction transaction = new Transaction();
//        transaction.setUser(user);
//        transaction.setType("Deposit");
//        transaction.setAmount(amount);
//        transaction.setDate(LocalDateTime.now());
//        transactionRepository.save(transaction);
//
//    }
//
//    @CacheEvict(value = "balances", key = "#userId")
//    public void withdraw(Long userId, BigDecimal amount) {
//        System.out.println("Sacando " + amount + " da conta do usuário " + userId);
//
//    }
//
//    @CacheEvict(value = "balances", key = "#userId")
//    public void payBill(Long userId, BigDecimal amount) {
//        System.out.println("Pagando conta de " + amount + " para o usuário " + userId);
//
//    }
//}
