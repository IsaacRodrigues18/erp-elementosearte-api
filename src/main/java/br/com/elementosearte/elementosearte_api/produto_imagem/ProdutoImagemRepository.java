package br.com.elementosearte.elementosearte_api.produto_imagem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoImagemRepository extends JpaRepository<ProdutoImagemEntity, Long> {

    List<ProdutoImagemEntity>findByProduto_IdProduto(Long idProduto);

    List<ProdutoImagemEntity>findByProduto_IdProdutoAndAtivoTrue(Long idProduto);

    List<ProdutoImagemEntity>findByProduto_IdProdutoAndAtivoTrueOrderByOrdemExibicaoAsc(Long idProduto);

    boolean existsByProduto_IdProdutoAndNomeArquivoAndAtivoTrue(Long idProduto, String nomeArquivo);
}