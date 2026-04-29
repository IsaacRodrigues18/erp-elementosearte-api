package br.com.elementosearte.elementosearte_api.produtos.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoResponseDTO {


    private Long idProduto;

    private String nome;

    private String descricao;

    private BigDecimal precoVendaReferencia;

    private boolean ativo;

    public ProdutoResponseDTO(){

    }

    public ProdutoResponseDTO(Long idProduto, String nome, String descricao,
                              BigDecimal precoVendaReferencia, boolean ativo) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.descricao = descricao;
        this.precoVendaReferencia = precoVendaReferencia;
        this.ativo = ativo;
    }



}
