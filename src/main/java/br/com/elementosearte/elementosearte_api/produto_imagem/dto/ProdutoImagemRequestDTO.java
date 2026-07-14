package br.com.elementosearte.elementosearte_api.produto_imagem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProdutoImagemRequestDTO {

    @NotNull(message = "O ID do produto é obrigatório")
    private Long idProduto;

    private boolean principal = false;

    private Integer ordemExibicao;
}