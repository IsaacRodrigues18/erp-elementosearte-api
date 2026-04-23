package br.com.elementosearte.elementosearte_api.categorias.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaRequestDTO {

    @NotBlank
    private String nome;

    private String descricao;

    public CategoriaRequestDTO(){

    }


}
