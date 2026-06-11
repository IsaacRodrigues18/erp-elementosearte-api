package br.com.elementosearte.elementosearte_api.movimentacao_estoque.dto;

import br.com.elementosearte.elementosearte_api.movimentacao_estoque.TipoMovimentacao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotNull
    private Long idProduto;

    @NotNull
    private Long idUsuario;

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    private BigDecimal valorUnitario;

    @NotNull
    private TipoMovimentacao tipoMovimentacao;

    private String observacao;


}
