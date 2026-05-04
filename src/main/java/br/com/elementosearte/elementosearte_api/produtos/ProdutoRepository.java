package br.com.elementosearte.elementosearte_api.produtos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    boolean existsByNomeProduto(String nomeProduto);

    List<ProdutoEntity> findByAtivoTrue();

    List<ProdutoEntity> findByAtivoFalse();

    List<ProdutoEntity> findAllByOrderByPrecoVendaReferenciaDesc();;
}
