package br.com.elementosearte.elementosearte_api.categorias.dto;


import br.com.elementosearte.elementosearte_api.produtos.dto.ProdutoResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaComProdutosResponseDTO {


    private Long idCategoria;
    private String nome;
    private List<ProdutoResponseDTO> produtos;


}
