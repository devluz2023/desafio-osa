package desafioOsa.desafioosa.do;

@Data @Builder
public class TransactionDTO {
     private String type;
    private BigDecimal valor;
    private String data;
}
