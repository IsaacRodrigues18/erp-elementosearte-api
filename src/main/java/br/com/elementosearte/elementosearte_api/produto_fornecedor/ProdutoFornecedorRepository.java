package br.com.elementosearte.elementosearte_api.produto_fornecedor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoFornecedorRepository extends JpaRepository<ProdutoFornecedorEntity, Long> {


    boolean existsByProduto_IdProdutoAndFornecedor_IdFornecedor(Long idProduto, Long idFornecedor);

    List<ProdutoFornecedorEntity> findByProduto_IdProdutoAndAtivoTrue(Long idProduto);

    List<ProdutoFornecedorEntity> findByFornecedor_IdFornecedorAndAtivoTrue(Long idFornecedor);


}
