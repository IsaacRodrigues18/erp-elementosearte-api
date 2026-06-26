package br.com.elementosearte.elementosearte_api.movimentacao_estoque.dto;

import br.com.elementosearte.elementosearte_api.movimentacao_estoque.TipoMovimentacao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimentacaoEstoqueRequestDTO {

    @NotNull(message = "A quantidade deve ser maior que zero")
    private Long idProduto;

    @NotNull(message = "O id do usuario deve ser informado")
    private Long idUsuario;

    @NotNull(message = "A quantidade deve ser informada")
    @PositiveOrZero(message = "Não é aceito valor negativo")
    private Integer quantidade;

    @NotNull(message = "O valor unitário deve ser informado")
    @PositiveOrZero(message = "O valor unitário não pode ser negativo")
    private BigDecimal valorUnitario;

    @NotNull(message = "O tipo de movimentação deve ser informado")
    private TipoMovimentacao tipoMovimentacao;

    private String observacao;

}
