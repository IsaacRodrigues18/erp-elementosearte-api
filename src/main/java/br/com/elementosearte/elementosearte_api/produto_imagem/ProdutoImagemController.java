package br.com.elementosearte.elementosearte_api.produto_imagem;

import br.com.elementosearte.elementosearte_api.produto_imagem.dto.ProdutoImagemRequestDTO;
import br.com.elementosearte.elementosearte_api.produto_imagem.dto.ProdutoImagemResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/produto-imagem")
public class ProdutoImagemController {

    private final ProdutoImagemService produtoImagemService;

    public ProdutoImagemController(ProdutoImagemService produtoImagemService) {
        this.produtoImagemService = produtoImagemService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProdutoImagemResponseDTO> adicionarImagemAoProduto(@Valid @ModelAttribute ProdutoImagemRequestDTO requestDTO, @RequestPart("arquivo") MultipartFile arquivo) {
        ProdutoImagemResponseDTO responseDTO = produtoImagemService.adicionarImagemAoProduto(requestDTO, arquivo);
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

    @PatchMapping("/{idProdutoImagem}/principal")
    public ResponseEntity<ProdutoImagemResponseDTO> definirImagemPrincipal(@PathVariable Long idProdutoImagem) {
        ProdutoImagemResponseDTO responseDTO = produtoImagemService.definirImagemPrincipal(idProdutoImagem);
        return ResponseEntity.ok(responseDTO);
    }

    @PatchMapping("/{idProdutoImagem}/ordenar")
    public ResponseEntity<List<ProdutoImagemResponseDTO>> ordenarImagemManualmente(@PathVariable Long idProdutoImagem, @RequestParam Integer novaOrdem) {
        List<ProdutoImagemResponseDTO> responseDTO = produtoImagemService.ordenarImagemManualmente(idProdutoImagem, novaOrdem);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{idProdutoImagem}")
    public ResponseEntity<Void> deletarImagem(@PathVariable Long idProdutoImagem) {
        produtoImagemService.deletarImagem(idProdutoImagem);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idProdutoImagem}/arquivo")
    public ResponseEntity<byte[]> buscarImagem(@PathVariable Long idProdutoImagem) {
        ProdutoImagemEntity imagem = produtoImagemService.buscarImagemPorId(idProdutoImagem);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(imagem.getTipoArquivo()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + imagem.getNomeArquivo() + "\"")
                .body(imagem.getDadosImagem());
    }
}