package br.com.elementosearte.elementosearte_api.produtos.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoRequestDTO {

    @NotBlank
    @Size(max = 255)
    private String nome;

    private String descricao;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal custoReferencia;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal precoVendaReferencia;

    @NotNull
    private Long idCategoria;

    public ProdutoRequestDTO() {

    }

    public ProdutoRequestDTO(String nome, String descricao, BigDecimal custoReferencia, BigDecimal precoVendaReferencia, Long idCategoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.custoReferencia = custoReferencia;
        this.precoVendaReferencia = precoVendaReferencia;
        this.idCategoria = idCategoria;
    }


}
