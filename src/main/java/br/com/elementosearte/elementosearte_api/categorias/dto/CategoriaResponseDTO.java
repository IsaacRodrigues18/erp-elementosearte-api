package br.com.elementosearte.elementosearte_api.categorias.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaResponseDTO {


    private Long idCategoria;

    private String nome;

    private String descricao;

    private boolean ativo;


    public CategoriaResponseDTO() {

    }

    public CategoriaResponseDTO(Long idCategoria, String nome, String descricao, boolean ativo) {
        this.idCategoria = idCategoria;
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;

    }

}


