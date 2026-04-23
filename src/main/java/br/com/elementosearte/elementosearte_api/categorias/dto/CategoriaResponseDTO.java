package br.com.elementosearte.elementosearte_api.categorias.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaResponseDTO {


    private Long id_categoria;

    private String nome;

    private String descricao;

    private boolean ativo;


    public CategoriaResponseDTO() {

    }

    public CategoriaResponseDTO(Long id_categoria, String nome, String descricao, boolean ativo) {
        this.id_categoria = id_categoria;
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;

    }

}


