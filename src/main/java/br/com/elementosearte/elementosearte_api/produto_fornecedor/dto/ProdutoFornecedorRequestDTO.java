package br.com.elementosearte.elementosearte_api.produto_fornecedor.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProdutoFornecedorRequestDTO {

    @NotNull(message = "Id do produto é obrigatório")
    private  Long idProduto;

    @NotNull(message = "Id do fornecedor é obrigatório")
    private Long idFornecedor;

    private boolean fornecedorPrincipal = false;


    @NotNull(message = "O custo do fornecedor é obrigatório")
    @PositiveOrZero(message = "O custo do fornecedor não pode ser negativo")
    private BigDecimal custoFornecedor;


}
