package br.com.elementosearte.elementosearte_api.produtos.dto.mapper;

import br.com.elementosearte.elementosearte_api.categorias.CategoriaEntity;
import br.com.elementosearte.elementosearte_api.produtos.ProdutoEntity;
import br.com.elementosearte.elementosearte_api.produtos.dto.ProdutoRequestDTO;
import br.com.elementosearte.elementosearte_api.produtos.dto.ProdutoResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {


    public ProdutoEntity toEntity(ProdutoRequestDTO produtoRequestDTO, CategoriaEntity categoria) {
        ProdutoEntity entity = new ProdutoEntity();
        entity.setNomeProduto(produtoRequestDTO.getNome());
        entity.setDescricaoProduto(produtoRequestDTO.getDescricao());
        entity.setCustoReferencia(produtoRequestDTO.getCustoReferencia());
        entity.setPrecoVendaReferencia(produtoRequestDTO.getPrecoVendaReferencia());
        entity.setCategoria(categoria);
        return entity;
    }

    public ProdutoResponseDTO toResponseDTO(ProdutoEntity produtoEntity) {
        ProdutoResponseDTO produtoDTO = new ProdutoResponseDTO(
                produtoEntity.getCategoria().getIdCategoria(),
                produtoEntity.getCategoria().getNome(),
                produtoEntity.getIdProduto(),
                produtoEntity.getNomeProduto(),
                produtoEntity.getDescricaoProduto(),
                produtoEntity.getCustoReferencia(),
                produtoEntity.getPrecoVendaReferencia(),
                produtoEntity.isAtivo()
        );
        return produtoDTO;
    }

}
