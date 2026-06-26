package br.com.elementosearte.elementosearte_api.produto_fornecedor;

import br.com.elementosearte.elementosearte_api.exceptions.BusinessException;
import br.com.elementosearte.elementosearte_api.exceptions.ResourceNotFoundException;
import br.com.elementosearte.elementosearte_api.fornecedor.FornecedorEntity;
import br.com.elementosearte.elementosearte_api.fornecedor.FornecedorRepository;
import br.com.elementosearte.elementosearte_api.produto_fornecedor.dto.ProdutoFornecedorResponseDTO;
import br.com.elementosearte.elementosearte_api.produto_fornecedor.dto.ProdutoFornecedorRequestDTO;
import br.com.elementosearte.elementosearte_api.produto_fornecedor.dto.mapper.ProdutoFornecedorMapper;
import br.com.elementosearte.elementosearte_api.produtos.ProdutoEntity;
import br.com.elementosearte.elementosearte_api.produtos.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoFornecedorService {


    private final ProdutoFornecedorRepository produtoFornecedorRepository;

    private final ProdutoRepository produtoRepository;

    private final FornecedorRepository fornecedorRepository;

    private final ProdutoFornecedorMapper produtoFornecedorMapper;

    public ProdutoFornecedorService(ProdutoFornecedorRepository produtoFornecedorRepository,
                                    ProdutoRepository produtoRepository,
                                    FornecedorRepository fornecedorRepository, ProdutoFornecedorMapper produtoFornecedorMapper) {
        this.produtoFornecedorRepository = produtoFornecedorRepository;
        this.produtoRepository = produtoRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.produtoFornecedorMapper = produtoFornecedorMapper;
    }


    public ProdutoFornecedorResponseDTO vincularFornecedorAoProduto(ProdutoFornecedorRequestDTO produtoFornecedorRequestDTO) {
        if (produtoFornecedorRepository.existsByProduto_IdProdutoAndFornecedor_IdFornecedor(
                produtoFornecedorRequestDTO.getIdProduto(),
                produtoFornecedorRequestDTO.getIdFornecedor())) {
            throw new BusinessException("Fornecedor já vinculado ao produto");
        }

        ProdutoEntity produto = produtoRepository.findById(produtoFornecedorRequestDTO.getIdProduto())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Produto não encontrado"));

        FornecedorEntity fornecedor = fornecedorRepository.findById(produtoFornecedorRequestDTO.getIdFornecedor())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Fornecedor não encontrado"));

        ProdutoFornecedorEntity produtoFornecedor = produtoFornecedorMapper.toEntity(produtoFornecedorRequestDTO, fornecedor, produto);

        ProdutoFornecedorEntity ProdutoFornecedorsalvo = produtoFornecedorRepository.save(produtoFornecedor);

        return produtoFornecedorMapper.toResponseDTO(ProdutoFornecedorsalvo);
    }

    public List<ProdutoFornecedorResponseDTO> listarFornecedoresDoProduto(Long idProduto) {

        if (!produtoRepository.existsById(idProduto)) {
            throw new ResourceNotFoundException("Produto não existe");
        }

        return produtoFornecedorRepository
                .findByProduto_IdProdutoAndAtivoTrue(idProduto)
                .stream()
                .map(produtoFornecedorMapper::toResponseDTO)
                .toList();
    }


    public List<ProdutoFornecedorResponseDTO> listarProdutosDoFornecedor(Long idFornecedor) {
        if (!fornecedorRepository.existsById(idFornecedor)) {
            throw new ResourceNotFoundException("Fornecedor não cadastrado");
        }

        return produtoFornecedorRepository
                .findByFornecedor_IdFornecedorAndAtivoTrue(idFornecedor)
                .stream()
                .map(produtoFornecedorMapper::toResponseDTO)
                .toList();

    }

    public void desativarRelacionametoProdutoFornecedor(Long idProdutoFornecedor) {
        ProdutoFornecedorEntity produtoFornecedor = produtoFornecedorRepository.findById(idProdutoFornecedor)
                .orElseThrow(() -> new ResourceNotFoundException("Vínculo produto-fornecedor não encontrado"));

        if (!produtoFornecedor.isAtivo()) {
            throw new BusinessException("Vínculo produto-fornecedor já está desativado");
        }
        produtoFornecedor.setAtivo(false);
        produtoFornecedorRepository.save(produtoFornecedor);
    }

    public void ativarRelacionamentoProdutoFornecedor(Long idProdutoFornecedor) {
        ProdutoFornecedorEntity produtoFornecedor = produtoFornecedorRepository.findById(idProdutoFornecedor)
                .orElseThrow(() -> new ResourceNotFoundException("Vínculo produto-fornecedor não encontrado"));

        if (produtoFornecedor.isAtivo()) {
            throw new BusinessException("Vínculo produto-fornecedor já está ativo");
        }

        produtoFornecedor.setAtivo(true);
        produtoFornecedorRepository.save(produtoFornecedor);

    }

    public void deletar(Long idProdutoFornecedor) {
        if (!produtoFornecedorRepository.existsById(idProdutoFornecedor)) {
            throw new ResourceNotFoundException("Vínculo produto-fornecedor não encontrado");
        }
        produtoFornecedorRepository.deleteById(idProdutoFornecedor);
    }

}
