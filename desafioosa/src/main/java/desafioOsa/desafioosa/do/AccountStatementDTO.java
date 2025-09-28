package desafioOsa.desafioosa.do;

@Data @Builder
public class AccountStatementDTO {
     private BigDecimal saldoTotal;
    private List<TransactionDTO> historico;
}
