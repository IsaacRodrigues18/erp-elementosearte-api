package br.com.elementosearte.elementosearte_api.movimentacao_estoque.dto;

import br.com.elementosearte.elementosearte_api.movimentacao_estoque.MovimentacaoEstoqueEntity;
import br.com.elementosearte.elementosearte_api.produtos.ProdutoEntity;
import br.com.elementosearte.elementosearte_api.usuarios.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class MovimentacaoEstoqueMapper {

    public MovimentacaoEstoqueResponseDTO toResponseDTO(MovimentacaoEstoqueEntity entity) {

        MovimentacaoEstoqueResponseDTO dto = new MovimentacaoEstoqueResponseDTO();

        dto.setIdMovimentacaoEstoque(entity.getIdMovimentacaoEstoque());

        dto.setIdProduto(entity.getProduto().getIdProduto());

        dto.setNomeProduto(entity.getProduto().getNomeProduto());

        dto.setIdUsuario(entity.getUsuario().getIdUsuario());

        dto.setNomeUsuario(entity.getUsuario().getNome());

        dto.setQuantidade(entity.getQuantidade());

        dto.setValorUnitario(entity.getValorUnitario());

        dto.setTipoMovimentacao(entity.getTipoMovimentacao());

        dto.setObservacao(entity.getObservacao());

        dto.setCriadoEm(entity.getCriadoEm());

        return dto;
    }

    public MovimentacaoEstoqueEntity toEntity(MovimentacaoEstoqueRequestDTO requestdto, ProdutoEntity produto,
                                              UsuarioEntity usuario) {

        MovimentacaoEstoqueEntity entity = new MovimentacaoEstoqueEntity();

        entity.setProduto(produto);

        entity.setUsuario(usuario);

        entity.setQuantidade(requestdto.getQuantidade());

        entity.setValorUnitario(requestdto.getValorUnitario());

        entity.setTipoMovimentacao(requestdto.getTipoMovimentacao());

        entity.setObservacao(requestdto.getObservacao());

        return entity;

    }


}
