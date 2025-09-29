package desafioOsa.desafioosa.dto;

import java.math.BigDecimal;
import java.util.List;

public class AccountStatementDTO {
    private BigDecimal saldoTotal;
    private List<TransactionDTO> historico;
}
