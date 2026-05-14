package br.com.elementosearte.elementosearte_api.produto_fornecedor;

import br.com.elementosearte.elementosearte_api.fornecedor.FornecedorEntity;
import br.com.elementosearte.elementosearte_api.fornecedor.FornecedorRepository;
import br.com.elementosearte.elementosearte_api.produto_fornecedor.dto.ProdutoFornecedorResponseDTO;
import br.com.elementosearte.elementosearte_api.produto_fornecedor.dto.ProdutoFornecedorRequestDTO;
import br.com.elementosearte.elementosearte_api.produtos.ProdutoEntity;
import br.com.elementosearte.elementosearte_api.produtos.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoFornecedorService {

    @Autowired
    private ProdutoFornecedorRepository produtoFornecedorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    private ProdutoFornecedorResponseDTO toDTO(ProdutoFornecedorEntity produtoFornecedor) {
        return new ProdutoFornecedorResponseDTO(
                produtoFornecedor.getIdProdutoFornecedor(),
                produtoFornecedor.getProduto().getIdProduto(),
                produtoFornecedor.getFornecedor().getIdFornecedor(),
                produtoFornecedor.isFornecedorPrincipal(),
                produtoFornecedor.isAtivo(),
                produtoFornecedor.getCustoFornecedor()

        );
    }

    public ProdutoFornecedorResponseDTO vincularFornecedorAoProduto(ProdutoFornecedorRequestDTO dto) {
        if (produtoFornecedorRepository.existsByProduto_IdProdutoAndFornecedor_IdFornecedor(
                dto.getIdProduto(),
                dto.getIdFornecedor())) {
            throw new IllegalArgumentException("Fornecedor já vinculado ao produto");
        }

        ProdutoEntity produto = produtoRepository.findById(dto.getIdProduto())
                .orElseThrow(() ->
                        new IllegalArgumentException("Produto não encontrado"));

        FornecedorEntity fornecedor = fornecedorRepository.findById(dto.getIdFornecedor())
                .orElseThrow(() ->
                        new IllegalArgumentException("Fornecedor não encontrado"));

        ProdutoFornecedorEntity produtoFornecedor = new ProdutoFornecedorEntity();

        produtoFornecedor.setProduto(produto);
        produtoFornecedor.setFornecedor(fornecedor);
        produtoFornecedor.setFornecedorPrincipal(dto.isFornecedorPrincipal());
        produtoFornecedor.setCustoFornecedor(dto.getCustoFornecedor());

        ProdutoFornecedorEntity salvo = produtoFornecedorRepository.save(produtoFornecedor);

        return toDTO(salvo);
    }

    public List<ProdutoFornecedorResponseDTO> listarFornecedoresDoProduto(Long idProduto) {

        if (!produtoRepository.existsById(idProduto)) {
            throw new IllegalArgumentException("Produto não existe");
        }

        return produtoFornecedorRepository
                .findByProduto_IdProdutoAndAtivoTrue(idProduto)
                .stream()
                .map(this::toDTO)
                .toList();
    }


    public List<ProdutoFornecedorResponseDTO> listarProdutosDoFornecedor(Long idFornecedor) {
        if (!fornecedorRepository.existsById(idFornecedor)) {
            throw new IllegalArgumentException("Fornecedor não cadastrado");
        }

        return produtoFornecedorRepository
                .findByFornecedor_IdFornecedorAndAtivoTrue(idFornecedor)
                .stream()
                .map(this::toDTO)
                .toList();

    }

    public void desativar(Long idProdutoFornecedor) {
        ProdutoFornecedorEntity produtoFornecedor = produtoFornecedorRepository.findById(idProdutoFornecedor)
                .orElseThrow(() -> new IllegalArgumentException("Vínculo produto-fornecedor) não encontrado"));

        produtoFornecedor.setAtivo(false);
        produtoFornecedorRepository.save(produtoFornecedor);

    }

    public void deletar(Long idProdutoFornecedor) {

        if (!produtoFornecedorRepository.existsById(idProdutoFornecedor)) {
            throw new IllegalArgumentException("Vínculo produto-fornecedor não encontrado");
        }

        produtoFornecedorRepository.deleteById(idProdutoFornecedor);
    }

}
