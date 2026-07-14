package br.com.elementosearte.elementosearte_api.produto_imagem.dto.mapper;

import br.com.elementosearte.elementosearte_api.produto_imagem.ProdutoImagemEntity;
import br.com.elementosearte.elementosearte_api.produto_imagem.dto.ProdutoImagemRequestDTO;
import br.com.elementosearte.elementosearte_api.produto_imagem.dto.ProdutoImagemResponseDTO;
import br.com.elementosearte.elementosearte_api.produtos.ProdutoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProdutoImagemMapper {

    public ProdutoImagemEntity toEntity(
            ProdutoImagemRequestDTO dto,
            ProdutoEntity produto,
            byte[] dadosImagem,
            String nomeArquivo,
            String tipoArquivo,
            Long tamanhoArquivo
    ){
        ProdutoImagemEntity entity =
                new ProdutoImagemEntity();

        entity.setProduto(produto);
        entity.setDadosImagem(dadosImagem);
        entity.setNomeArquivo(nomeArquivo);
        entity.setTipoArquivo(tipoArquivo);
        entity.setTamanhoArquivo(tamanhoArquivo);
        entity.setPrincipal(dto.isPrincipal());

        entity.setOrdemExibicao(
                dto.getOrdemExibicao() != null
                        ? dto.getOrdemExibicao()
                        : 1
        );

        return entity;
    }

    public ProdutoImagemResponseDTO toResponseDTO(
            ProdutoImagemEntity entity
    ) {
        ProdutoImagemResponseDTO response =
                new ProdutoImagemResponseDTO();

        response.setIdProdutoImagem(
                entity.getIdProdutoImagem()
        );

        response.setIdProduto(
                entity.getProduto().getIdProduto()
        );

        response.setNomeProduto(
                entity.getProduto().getNomeProduto()
        );

        response.setNomeArquivo(
                entity.getNomeArquivo()
        );

        response.setTipoArquivo(
                entity.getTipoArquivo()
        );

        response.setTamanhoArquivo(
                entity.getTamanhoArquivo()
        );

        response.setPrincipal(
                entity.isPrincipal()
        );

        response.setAtivo(
                entity.isAtivo()
        );

        response.setOrdemExibicao(
                entity.getOrdemExibicao()
        );

        response.setUrlImagem(
                "/produto-imagem/"
                        + entity.getIdProdutoImagem()
                        + "/arquivo"
        );

        return response;
    }
}