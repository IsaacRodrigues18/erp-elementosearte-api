package br.com.elementosearte.elementosearte_api.movimentacao_estoque;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoqueEntity, Long> {

    List<MovimentacaoEstoqueEntity>  findByProdutoIdProduto(Long idProduto);

    List<MovimentacaoEstoqueEntity> findByUsuarioIdUsuario(Long idUsuario);
}
