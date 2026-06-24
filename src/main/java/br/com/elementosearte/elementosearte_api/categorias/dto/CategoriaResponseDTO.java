package br.com.elementosearte.elementosearte_api.categorias.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponseDTO {


    private Long idCategoria;

    private String nomeCategoria;

    private String descricao;

    private boolean ativo;



}


