package br.com.elementosearte.elementosearte_api.produtos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponseDTO {

    private Long idCategoria;

    private String nomeCategoria;

    private Long idProduto;

    private String nome;

    private String descricao;

    private BigDecimal custoReferencia;

    private BigDecimal precoVendaReferencia;

    private Integer quantidadeEstoque;

    private boolean ativo;


}
