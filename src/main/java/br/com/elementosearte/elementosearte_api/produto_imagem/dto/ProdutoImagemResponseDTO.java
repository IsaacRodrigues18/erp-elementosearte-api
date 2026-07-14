package br.com.elementosearte.elementosearte_api.produto_imagem.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProdutoImagemResponseDTO {

    private Long idProdutoImagem;

    private Long idProduto;

    private String nomeProduto;

    private String nomeArquivo;

    private String urlImagem;

    private boolean principal;

    private boolean ativo;

    private Integer ordemExibicao;

}
