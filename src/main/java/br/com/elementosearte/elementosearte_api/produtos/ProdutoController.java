package br.com.elementosearte.elementosearte_api.produtos;

import br.com.elementosearte.elementosearte_api.produtos.dto.ProdutoRequestDTO;
import br.com.elementosearte.elementosearte_api.produtos.dto.ProdutoResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;


    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criarProduto(@RequestBody @Valid ProdutoRequestDTO produtoDTO) {
        ProdutoResponseDTO produtoResponseDTO = produtoService.criarProduto(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutos() {
        List<ProdutoResponseDTO> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{idProduto}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long idProduto) {
        ProdutoResponseDTO produto = produtoService.buscarProdutoPorId(idProduto);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<ProdutoResponseDTO>> listarPorCategoria(@PathVariable Long idCategoria) {
        List<ProdutoResponseDTO> produtos = produtoService.listarProdutosPorCategoria(idCategoria);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutosAtivos() {
        List<ProdutoResponseDTO> produtos = produtoService.listarProdutosAtivos();
        return ResponseEntity.ok(produtos);

    }

    @GetMapping("/inativos")
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutosInativos() {
        List<ProdutoResponseDTO> produtos = produtoService.listarProdutosInativos();
        return ResponseEntity.ok(produtos);

    }

    @GetMapping("/maior-preco")
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutosComMaiorPrecoVenda() {
        List<ProdutoResponseDTO> produtos = produtoService.listarPorMaiorPrecoVendaReferencia();
        return ResponseEntity.ok(produtos);
    }

    @PutMapping("/{idProduto}")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(
            @PathVariable Long idProduto,
            @RequestBody @Valid ProdutoRequestDTO produtoDTO) {

        ProdutoResponseDTO produtoAtualizado =
                produtoService.atualizarProduto(idProduto, produtoDTO);

        return ResponseEntity.ok(produtoAtualizado);
    }

    @PatchMapping("/inativar/{idProduto}")
    public ResponseEntity<Void> inativarProduto(@PathVariable Long idProduto) {
        produtoService.inativarProduto(idProduto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/ativar/{idProduto}")
    public ResponseEntity<Void> ativarProduto(@PathVariable Long idProduto) {
        produtoService.ativarProduto(idProduto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idProduto}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long idProduto) {
        produtoService.excluirProduto(idProduto);
        return ResponseEntity.noContent().build();
    }


}

