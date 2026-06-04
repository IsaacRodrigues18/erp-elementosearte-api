package br.com.elementosearte.elementosearte_api.produto_imagem;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProdutoImagemRepository extends JpaRepository<ProdutoImagemEntity, Long> {

    List<ProdutoImagemEntity> findByProdutoIdProduto(Long idProduto);

    List<ProdutoImagemEntity> findByProdutoIdProdutoAndAtivoTrue(Long idProduto);

    List<ProdutoImagemEntity> findByProdutoIdProdutoAndAtivoTrueOrderByOrdemExibicaoAsc(Long idProduto);





}
