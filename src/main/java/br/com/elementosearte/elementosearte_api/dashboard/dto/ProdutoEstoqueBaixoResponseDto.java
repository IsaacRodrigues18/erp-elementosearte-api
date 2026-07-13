package br.com.elementosearte.elementosearte_api.dashboard.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProdutoEstoqueBaixoResponseDto {

    private Long idProduto;
    private String nomeProduto;
    private Integer quantidadeEstoque;

}
