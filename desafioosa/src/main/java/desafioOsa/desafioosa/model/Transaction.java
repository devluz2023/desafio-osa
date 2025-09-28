// package desafioOsa.desafioosa.model;

// import java.math.BigDecimal;
// import java.time.LocalDateTime;

// import org.springframework.data.annotation.Id;

// @Entity
// @Table(name = "transactions")
// @Builder
// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// @EqualsAndHashCode(onlyExplicitlyIncluded = true)
// public class Transaction {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "user_id", nullable = false)
//     private User user;

//     @Column(nullable = false)
//     private String type; // Deposit, Withdrawal, Payment

//     @Column(nullable = false)
//     private BigDecimal amount;

//     @Column(nullable = false)
//     private LocalDateTime date;

// }
