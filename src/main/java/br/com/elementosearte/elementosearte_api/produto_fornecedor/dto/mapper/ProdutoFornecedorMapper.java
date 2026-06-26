package br.com.elementosearte.elementosearte_api.produto_fornecedor.dto.mapper;

import br.com.elementosearte.elementosearte_api.fornecedor.FornecedorEntity;
import br.com.elementosearte.elementosearte_api.produto_fornecedor.ProdutoFornecedorEntity;
import br.com.elementosearte.elementosearte_api.produto_fornecedor.dto.ProdutoFornecedorRequestDTO;
import br.com.elementosearte.elementosearte_api.produto_fornecedor.dto.ProdutoFornecedorResponseDTO;
import br.com.elementosearte.elementosearte_api.produtos.ProdutoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProdutoFornecedorMapper {

    public ProdutoFornecedorEntity toEntity(ProdutoFornecedorRequestDTO produtoFornecedorRequestDTO,
                                            FornecedorEntity fornecedor, ProdutoEntity produto) {
        ProdutoFornecedorEntity entity = new ProdutoFornecedorEntity();
        entity.setProduto(produto);
        entity.setFornecedor(fornecedor);
        entity.setFornecedorPrincipal(produtoFornecedorRequestDTO.isFornecedorPrincipal());
        entity.setCustoFornecedor(produtoFornecedorRequestDTO.getCustoFornecedor());
        return entity;
    }

    public ProdutoFornecedorResponseDTO toResponseDTO(ProdutoFornecedorEntity entity) {
        ProdutoFornecedorResponseDTO responseDTO = new ProdutoFornecedorResponseDTO();
        responseDTO.setIdProdutoFornecedor(entity.getIdProdutoFornecedor());
        responseDTO.setIdProduto(entity.getProduto().getIdProduto());
        responseDTO.setIdFornecedor(entity.getFornecedor().getIdFornecedor());
        responseDTO.setFornecedorPrincipal(entity.isFornecedorPrincipal());
        responseDTO.setAtivo(entity.isAtivo());
        responseDTO.setCustoFornecedor(entity.getCustoFornecedor());
        return responseDTO;
    }
}
