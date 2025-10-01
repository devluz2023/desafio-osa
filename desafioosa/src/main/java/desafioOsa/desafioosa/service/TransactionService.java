package desafioOsa.desafioosa.service;

import desafioOsa.desafioosa.dto.SaldoResponseDTO;
import desafioOsa.desafioosa.dto.TransactionDTO;
import desafioOsa.desafioosa.dto.TransactionType;
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
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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

    public BigDecimal getAverageTransactionAmount(Long userId) {
        System.out.println("Calculando média das transações do banco de dados para o usuário " + userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Transaction> transactions = transactionRepository.findByUserAndType(user, TransactionType.DEPOSIT.toString());

        if (transactions.isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal total = transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        int count = transactions.size();

        BigDecimal average = total.divide(BigDecimal.valueOf(count), 2, BigDecimal.ROUND_HALF_UP);

        return average;
    }

    @CacheEvict(value = "balances", key = "#userId")
    public SaldoResponseDTO getTransactionHistory(Long userId) {
        System.out.println("Buscando histórico de transações do banco de dados para o usuário " + userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<Transaction> transactions = transactionRepository.findByUser(user);
        BigDecimal saldoTotal = transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm:ss");
        List<TransactionDTO> historicoDTO = transactions.stream()
                .map(t -> TransactionDTO.builder().type(t.getType())
                        .valor(t.getAmount())
                        .data(t.getDate().format(formatter))
                        .build())
                .collect(Collectors.toList());

        return SaldoResponseDTO.builder().saldoTotal(saldoTotal).historico(historicoDTO).build();

    }

    @CacheEvict(value = "balances", key = "#userId")
    public void deposit(Long userId, BigDecimal amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        Transaction depositTransaction = new Transaction();
        depositTransaction.setUser(user);
        depositTransaction.setType(TransactionType.DEPOSIT.toString());
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

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setType(TransactionType.WITHDRAWAL.toString());
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
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            BigDecimal limitAllowed = this.getAverageTransactionAmount(userId);
            if (newBalance.compareTo(limitAllowed.negate()) < 0) {
                throw new IllegalArgumentException("New balance exceeds allowed limit.");
            }
        }


        if (currentBalance.compareTo(BigDecimal.ZERO) < 0) {
            BigDecimal interestCharged = amount.multiply(INTEREST_RATE).setScale(2, RoundingMode.HALF_UP);
            

            Transaction interestTransaction = new Transaction();
            interestTransaction.setUser(user);
            interestTransaction.setType(TransactionType.PAYMENT.toString());
            interestTransaction.setAmount(interestCharged);
            interestTransaction.setDate(LocalDateTime.now());
            transactionRepository.save(interestTransaction);


        }else if(newBalance.compareTo(BigDecimal.ZERO)<0){
      
            BigDecimal interestCharged = newBalance.multiply(INTEREST_RATE).setScale(2, RoundingMode.HALF_UP);
            interestCharged = interestCharged.add(newBalance);
            Transaction interestTransaction = new Transaction();
            interestTransaction.setUser(user);
            interestTransaction.setType(TransactionType.PAYMENT.toString());
            interestTransaction.setAmount(interestCharged);
            interestTransaction.setDate(LocalDateTime.now());
            transactionRepository.save(interestTransaction);

         
        }else{
            Transaction transaction = new Transaction();
            transaction.setUser(user);
            transaction.setType(TransactionType.PAYMENT.toString());
            transaction.setAmount(amount.negate());
            transaction.setDate(LocalDateTime.now());
            transactionRepository.save(transaction);
        }
  
          

        System.out.println("Pagando conta de " + amount + " para o usuário " + userId);

    }
}
