package br.com.elementosearte.elementosearte_api.produto_imagem.dto;

import jakarta.validation.constraints.NotBlank;
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

    @NotNull
    private Long idProduto;

    @NotBlank
    private String urlImagem;

    @NotBlank
    private String nomeArquivo;

    private boolean principal;

    private Integer ordemExibicao;

}
