package desafioOsa.desafioosa.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaldoResponseDTO {
    private BigDecimal saldoTotal;
    private List<TransactionDTO> historico;
}
