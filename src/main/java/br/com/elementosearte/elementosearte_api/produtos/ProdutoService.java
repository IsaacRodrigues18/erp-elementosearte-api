package br.com.elementosearte.elementosearte_api.produtos;

import br.com.elementosearte.elementosearte_api.categorias.CategoriaEntity;
import br.com.elementosearte.elementosearte_api.categorias.CategoriaRepository;
import br.com.elementosearte.elementosearte_api.exceptions.BusinessException;
import br.com.elementosearte.elementosearte_api.exceptions.ResourceNotFoundException;
import br.com.elementosearte.elementosearte_api.produtos.dto.ProdutoRequestDTO;
import br.com.elementosearte.elementosearte_api.produtos.dto.ProdutoResponseDTO;
import br.com.elementosearte.elementosearte_api.produtos.dto.mapper.ProdutoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {


    private final ProdutoRepository produtoRepository;

    private final CategoriaRepository categoriaRepository;

    private final ProdutoMapper produtoMapper;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.produtoMapper = produtoMapper;
    }


    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO produtoRequestDTO) {
        if (produtoRepository.existsByNomeProduto(produtoRequestDTO.getNome())) {
            throw new BusinessException("Produto já cadastrado");
        }

        CategoriaEntity categoria = categoriaRepository.findById(produtoRequestDTO.getIdCategoria())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

        ProdutoEntity produto = produtoMapper.toEntity(produtoRequestDTO, categoria);

        ProdutoEntity produtoSalvo = produtoRepository.save(produto);

        return produtoMapper.toResponseDTO(produtoSalvo);
    }

    public List<ProdutoResponseDTO> listarProdutos() {
        return produtoRepository.findAll()
                .stream()
                .map(produtoMapper::toResponseDTO)
                .toList();
    }

    public ProdutoResponseDTO buscarProdutoPorId(Long idProduto) {
        ProdutoEntity produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        return produtoMapper.toResponseDTO(produto);
    }

    public List<ProdutoResponseDTO> listarProdutosPorCategoria(Long idCategoria) {
        CategoriaEntity categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

        return categoria.getProdutos()
                .stream()
                .map(produtoMapper::toResponseDTO)
                .toList();
    }

    public List<ProdutoResponseDTO> listarProdutosAtivos() {
        return produtoRepository.findByAtivoTrue()
                .stream()
                .map(produtoMapper::toResponseDTO)
                .toList();
    }

    public List<ProdutoResponseDTO> listarProdutosInativos() {
        return produtoRepository.findByAtivoFalse()
                .stream()
                .map(produtoMapper::toResponseDTO)

                .toList();
    }

    public List<ProdutoResponseDTO> listarPorMaiorPrecoVendaReferencia() {
        return produtoRepository.findAllByOrderByPrecoVendaReferenciaDesc()
                .stream()
                .map(produtoMapper::toResponseDTO)
                .toList();
    }

    public ProdutoResponseDTO atualizarProduto(Long idProduto, ProdutoRequestDTO produtoRequestDTO) {
        ProdutoEntity produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        if (!produto.getNomeProduto().equals(produtoRequestDTO.getNome())
                && produtoRepository.existsByNomeProduto(produtoRequestDTO.getNome())) {
            throw new BusinessException("Produto já cadastrado");
        }

        CategoriaEntity categoria = categoriaRepository.findById(produtoRequestDTO.getIdCategoria())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

        produto.setNomeProduto(produtoRequestDTO.getNome());
        produto.setDescricaoProduto(produtoRequestDTO.getDescricao());
        produto.setCustoReferencia(produtoRequestDTO.getCustoReferencia());
        produto.setPrecoVendaReferencia(produtoRequestDTO.getPrecoVendaReferencia());
        produto.setCategoria(categoria);

        ProdutoEntity produtoAtualizado = produtoRepository.save(produto);

        return produtoMapper.toResponseDTO(produtoAtualizado);
    }

    public void inativarProduto(Long idProduto) {
        ProdutoEntity produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        produto.setAtivo(false);
        produtoRepository.save(produto);
    }

    public void excluirProduto(Long idProduto) {
        if (!produtoRepository.existsById(idProduto)) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }

        produtoRepository.deleteById(idProduto);
    }
}