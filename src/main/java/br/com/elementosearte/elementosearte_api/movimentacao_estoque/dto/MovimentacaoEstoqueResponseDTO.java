package br.com.elementosearte.elementosearte_api.movimentacao_estoque.dto;

import br.com.elementosearte.elementosearte_api.movimentacao_estoque.TipoMovimentacao;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoEstoqueResponseDTO {


    private Long idMovimentacaoEstoque;

    private  Long idProduto;
    private String nomeProduto;

    private  Long idUsuario;
    private String nomeUsuario;

    private Integer quantidade;

    private BigDecimal valorUnitario;

    private TipoMovimentacao tipoMovimentacao;

    private String observacao;

    private LocalDateTime criadoEm;



}
