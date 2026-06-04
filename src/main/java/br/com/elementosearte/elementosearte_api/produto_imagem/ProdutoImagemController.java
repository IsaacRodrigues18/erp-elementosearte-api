package br.com.elementosearte.elementosearte_api.produto_imagem;

import br.com.elementosearte.elementosearte_api.produto_imagem.dto.ProdutoImagemRequestDTO;
import br.com.elementosearte.elementosearte_api.produto_imagem.dto.ProdutoImagemResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto-imagem")
public class ProdutoImagemController {

    @Autowired
    private ProdutoImagemService produtoImagemService;

    @PostMapping
    public ResponseEntity<ProdutoImagemResponseDTO> adicionarImagemAoProduto(@RequestBody @Valid ProdutoImagemRequestDTO requestDTO) {
        ProdutoImagemResponseDTO responseDTO = produtoImagemService.adicionarImagemAoProduto(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/produto/{idProduto}")
    public ResponseEntity<List<ProdutoImagemResponseDTO>> listarImagensDoProduto(@PathVariable Long idProduto) {
        return ResponseEntity.ok(produtoImagemService.listarImagensDoProduto(idProduto));
    }

    @GetMapping("/produto/{idProduto}/ativas")
    public ResponseEntity<List<ProdutoImagemResponseDTO>> listarImagensAtivas(@PathVariable Long idProduto) {
        return ResponseEntity.ok(produtoImagemService.listarImagensAtivas(idProduto));
    }

    @PatchMapping("/{idProdutoImagem}/ordenar")
    public ResponseEntity<List<ProdutoImagemResponseDTO>> ordenarImagemManualmente(@PathVariable Long idProdutoImagem, @RequestParam Integer novaOrdem) {
        List<ProdutoImagemResponseDTO> responseDTO = produtoImagemService.ordenarImagemManualmente(idProdutoImagem, novaOrdem);
        return ResponseEntity.ok(responseDTO);
    }

    @PatchMapping("/{idProdutoImagem}/principal")
    public ResponseEntity<ProdutoImagemResponseDTO> definirImagemPrincipal(@PathVariable Long idProdutoImagem) {
        ProdutoImagemResponseDTO responseDTO =  produtoImagemService.definirImagemPrincipal(idProdutoImagem);
        return ResponseEntity.ok(responseDTO);
    }



    @DeleteMapping("/{idProdutoImagem}")
    public ResponseEntity<Void> deletarImagem(@PathVariable Long idProdutoImagem) {
        produtoImagemService.deletarImagem(idProdutoImagem);
        return ResponseEntity.noContent().build();
    }

}
