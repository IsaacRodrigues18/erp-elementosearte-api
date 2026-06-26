package br.com.elementosearte.elementosearte_api.produto_fornecedor;

import br.com.elementosearte.elementosearte_api.produto_fornecedor.dto.ProdutoFornecedorRequestDTO;
import br.com.elementosearte.elementosearte_api.produto_fornecedor.dto.ProdutoFornecedorResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto-fornecedor")
public class ProdutoFornecedorController {

    @Autowired
    private ProdutoFornecedorService produtoFornecedorService;

    @PostMapping
    public ResponseEntity<ProdutoFornecedorResponseDTO> criarVinculo(@RequestBody @Valid ProdutoFornecedorRequestDTO dto){
        ProdutoFornecedorResponseDTO produtoFornecedor = produtoFornecedorService.vincularFornecedorAoProduto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoFornecedor);
    }

    @GetMapping("/produto/{idProduto}/fornecedores")
    public ResponseEntity<List<ProdutoFornecedorResponseDTO>> listarFornecedoresDoProduto(@PathVariable Long idProduto) {
        List<ProdutoFornecedorResponseDTO> fornecedores = produtoFornecedorService.listarFornecedoresDoProduto(idProduto);
        return ResponseEntity.ok(fornecedores);
    }
    @GetMapping("/fornecedor/{idFornecedor}/produtos")
    public ResponseEntity<List<ProdutoFornecedorResponseDTO>> listarProdutosDoFornecedor(@PathVariable Long idFornecedor) {
        List<ProdutoFornecedorResponseDTO> produtos = produtoFornecedorService.listarProdutosDoFornecedor(idFornecedor);
        return ResponseEntity.ok(produtos);
    }

    @PatchMapping("/{idProdutoFornecedor}/desativarVinculo")
    public ResponseEntity<Void> inativarVinculo(@PathVariable Long idProdutoFornecedor) {
        produtoFornecedorService.desativarRelacionametoProdutoFornecedor(idProdutoFornecedor);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{idProdutofornecedor}/ativarVinculo")
    public ResponseEntity<Void> ativarVinculo(@PathVariable Long idProdutofornecedor) {
        produtoFornecedorService.ativarRelacionamentoProdutoFornecedor(idProdutofornecedor);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idProdutoFornecedor}")
    public ResponseEntity<Void> deletarVinculo(@PathVariable long idProdutoFornecedor) {
        produtoFornecedorService.deletar(idProdutoFornecedor);
        return ResponseEntity.noContent().build();
    }
}
