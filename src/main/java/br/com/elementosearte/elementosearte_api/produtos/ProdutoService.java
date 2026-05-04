package br.com.elementosearte.elementosearte_api.produtos;

import br.com.elementosearte.elementosearte_api.categorias.CategoriaEntity;
import br.com.elementosearte.elementosearte_api.categorias.CategoriaRepository;
import br.com.elementosearte.elementosearte_api.produtos.dto.ProdutoRequestDTO;
import br.com.elementosearte.elementosearte_api.produtos.dto.ProdutoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    private ProdutoResponseDTO toDTO(ProdutoEntity produto) {
        return new ProdutoResponseDTO(
                produto.getIdProduto(),
                produto.getNomeProduto(),
                produto.getDescricaoProduto(),
                produto.getPrecoVendaReferencia(),
                produto.isAtivo()
        );
    }

    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO produtoRequestDTO) {
        if (produtoRepository.existsByNomeProduto(produtoRequestDTO.getNome())) {
            throw new IllegalArgumentException("Produto já cadastrado");
        }

        CategoriaEntity categoria = categoriaRepository.findById(produtoRequestDTO.getIdCategoria())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        ProdutoEntity produto = new ProdutoEntity();
        produto.setNomeProduto(produtoRequestDTO.getNome());
        produto.setDescricaoProduto(produtoRequestDTO.getDescricao());
        produto.setCustoReferencia(produtoRequestDTO.getCustoReferencia());
        produto.setPrecoVendaReferencia(produtoRequestDTO.getPrecoVendaReferencia());
        produto.setCategoria(categoria);

        ProdutoEntity produtoSalvo = produtoRepository.save(produto);

        return toDTO(produtoSalvo);
    }

    public List<ProdutoResponseDTO> listarProdutos() {
        return produtoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ProdutoResponseDTO buscarProdutoPorId(Long idProduto) {
        ProdutoEntity produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        return toDTO(produto);
    }

    public List<ProdutoResponseDTO> listarProdutosPorCategoria(Long idCategoria) {
        CategoriaEntity categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        return categoria.getProdutos()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<ProdutoResponseDTO> listarProdutosAtivos() {
        return produtoRepository.findByAtivoTrue()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<ProdutoResponseDTO> listarProdutosInativos() {
        return produtoRepository.findByAtivoFalse()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<ProdutoResponseDTO> listarPorMaiorPrecoVendaReferencia() {
        return produtoRepository.findAllByOrderByPrecoVendaReferenciaDesc()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ProdutoResponseDTO atualizarProduto(Long idProduto, ProdutoRequestDTO produtoRequestDTO) {
        ProdutoEntity produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if (!produto.getNomeProduto().equals(produtoRequestDTO.getNome())
                && produtoRepository.existsByNomeProduto(produtoRequestDTO.getNome())) {
            throw new IllegalArgumentException("Produto já cadastrado");
        }

        CategoriaEntity categoria = categoriaRepository.findById(produtoRequestDTO.getIdCategoria())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        produto.setNomeProduto(produtoRequestDTO.getNome());
        produto.setDescricaoProduto(produtoRequestDTO.getDescricao());
        produto.setCustoReferencia(produtoRequestDTO.getCustoReferencia());
        produto.setPrecoVendaReferencia(produtoRequestDTO.getPrecoVendaReferencia());
        produto.setCategoria(categoria);

        ProdutoEntity produtoAtualizado = produtoRepository.save(produto);

        return toDTO(produtoAtualizado);
    }

    public void inativarProduto(Long idProduto) {
        ProdutoEntity produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        produto.setAtivo(false);
        produtoRepository.save(produto);
    }

    public void excluirProduto(Long idProduto) {
        if (!produtoRepository.existsById(idProduto)) {
            throw new IllegalArgumentException("Produto não encontrado");
        }

        produtoRepository.deleteById(idProduto);
    }
}