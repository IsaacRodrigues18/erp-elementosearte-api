package br.com.elementosearte.elementosearte_api.produtos.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequestDTO {

    @NotBlank(message = "Nome do produto obrigatório")
    @Size(max = 255)
    private String nome;

    private String descricao;

    @NotNull(message = "Custo de referência é obrigatório")
    @DecimalMin(value = "0.00", message = "Custo não pode ser negativo")
    private BigDecimal custoReferencia;

    @NotNull(message = "Preço de venda é obrigatório")
    @DecimalMin(value = "0.00", message = "Preço de venda não pode ser negativo")
    private BigDecimal precoVendaReferencia;

    @NotNull(message = "Categoria é obrigatória")
    private Long idCategoria;


}
