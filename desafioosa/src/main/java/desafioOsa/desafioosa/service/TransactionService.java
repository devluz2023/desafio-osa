package desafioOsa.desafioosa.service;

import desafioOsa.desafioosa.model.Transaction;
import desafioOsa.desafioosa.model.User;
import desafioOsa.desafioosa.repository.TransactionRepository;
import desafioOsa.desafioosa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    private static final BigDecimal INTEREST_RATE = new BigDecimal("1.02");

    @Cacheable(value = "balances", key = "#userId")
    public BigDecimal getBalance(Long userId) {
        System.out.println("Buscando saldo do banco de dados para o usuário " + userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Transaction> transactions = transactionRepository.findByUser(user);

        BigDecimal balance = transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return balance;

    }

    @Cacheable(value = "transactionHistories", key = "#userId")
    public List<Transaction> getTransactionHistory(Long userId) {
        System.out.println("Buscando histórico de transações do banco de dados para o usuário " + userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return transactionRepository.findByUser(user);
    }

    @CacheEvict(value = "balances", key = "#userId")
    public void deposit(Long userId, BigDecimal amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        BigDecimal currentBalance = getBalance(userId);

        if (currentBalance.compareTo(BigDecimal.ZERO) < 0) {
            BigDecimal interestCharged = currentBalance.multiply(INTEREST_RATE).setScale(2, RoundingMode.HALF_UP);

            Transaction interestTransaction = new Transaction();
            interestTransaction.setUser(user);
            interestTransaction.setType("Juros Saldo Negativo");
            interestTransaction.setAmount(interestCharged.negate());
            interestTransaction.setDate(LocalDateTime.now());
            transactionRepository.save(interestTransaction);

            currentBalance = currentBalance.subtract(interestCharged);
        }

        Transaction depositTransaction = new Transaction();
        depositTransaction.setUser(user);
        depositTransaction.setType("Depósito");
        depositTransaction.setAmount(amount);
        depositTransaction.setDate(LocalDateTime.now());
        transactionRepository.save(depositTransaction);

    }

    @CacheEvict(value = "balances", key = "#userId")
    public void withdraw(Long userId, BigDecimal amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }

        BigDecimal currentBalance = getBalance(userId);

        if (currentBalance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        BigDecimal newBalance = currentBalance.subtract(amount);

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setType("Withdrawal");
        transaction.setAmount(amount.negate());
        transaction.setDate(LocalDateTime.now());
        transactionRepository.save(transaction);

        System.out.println("Sacando " + amount + " da conta do usuário " + userId);

    }

    @CacheEvict(value = "balances", key = "#userId")
    public void payBill(Long userId, BigDecimal amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Bill amount must be positive");
        }

        BigDecimal currentBalance = getBalance(userId);

        BigDecimal newBalance = currentBalance.subtract(amount);
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setType("Payment");
        transaction.setAmount(amount.negate());
        transaction.setDate(LocalDateTime.now());
        transactionRepository.save(transaction);

        System.out.println("Pagando conta de " + amount + " para o usuário " + userId);

    }
}
