package br.com.elementosearte.elementosearte_api.movimentacao_estoque.dto.mapper;

import br.com.elementosearte.elementosearte_api.movimentacao_estoque.MovimentacaoEstoqueEntity;
import br.com.elementosearte.elementosearte_api.movimentacao_estoque.dto.MovimentacaoEstoqueRequestDTO;
import br.com.elementosearte.elementosearte_api.movimentacao_estoque.dto.MovimentacaoEstoqueResponseDTO;
import br.com.elementosearte.elementosearte_api.produtos.ProdutoEntity;
import br.com.elementosearte.elementosearte_api.usuarios.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class MovimentacaoEstoqueMapper {

    public MovimentacaoEstoqueResponseDTO toResponseDTO(MovimentacaoEstoqueEntity movimentacaoEstoqueEntity) {

        MovimentacaoEstoqueResponseDTO movimentacaoEstoqueResponseDTO = new MovimentacaoEstoqueResponseDTO();

        movimentacaoEstoqueResponseDTO.setIdMovimentacaoEstoque(movimentacaoEstoqueEntity.getIdMovimentacaoEstoque());

        movimentacaoEstoqueResponseDTO.setIdProduto(movimentacaoEstoqueEntity.getProduto().getIdProduto());

        movimentacaoEstoqueResponseDTO.setNomeProduto(movimentacaoEstoqueEntity.getProduto().getNomeProduto());

        movimentacaoEstoqueResponseDTO.setIdUsuario(movimentacaoEstoqueEntity.getUsuario().getIdUsuario());

        movimentacaoEstoqueResponseDTO.setNomeUsuario(movimentacaoEstoqueEntity.getUsuario().getNome());

        movimentacaoEstoqueResponseDTO.setQuantidade(movimentacaoEstoqueEntity.getQuantidade());

        movimentacaoEstoqueResponseDTO.setValorUnitario(movimentacaoEstoqueEntity.getValorUnitario());

        movimentacaoEstoqueResponseDTO.setTipoMovimentacao(movimentacaoEstoqueEntity.getTipoMovimentacao());

        movimentacaoEstoqueResponseDTO.setObservacao(movimentacaoEstoqueEntity.getObservacao());

        movimentacaoEstoqueResponseDTO.setCriadoEm(movimentacaoEstoqueEntity.getCriadoEm());

        return movimentacaoEstoqueResponseDTO;
    }

    public MovimentacaoEstoqueEntity toEntity(MovimentacaoEstoqueRequestDTO movimentacaoEstoqueRequestDTO, ProdutoEntity produto,
                                              UsuarioEntity usuario) {

        MovimentacaoEstoqueEntity movimentacaoEstoqueEntity = new MovimentacaoEstoqueEntity();

        movimentacaoEstoqueEntity.setProduto(produto);

        movimentacaoEstoqueEntity.setUsuario(usuario);

        movimentacaoEstoqueEntity.setQuantidade(movimentacaoEstoqueRequestDTO.getQuantidade());

        movimentacaoEstoqueEntity.setValorUnitario(movimentacaoEstoqueRequestDTO.getValorUnitario());

        movimentacaoEstoqueEntity.setTipoMovimentacao(movimentacaoEstoqueRequestDTO.getTipoMovimentacao());

        movimentacaoEstoqueEntity.setObservacao(movimentacaoEstoqueRequestDTO.getObservacao());

        return movimentacaoEstoqueEntity;

    }


}
