package br.com.elementosearte.elementosearte_api.movimentacao_estoque;

import br.com.elementosearte.elementosearte_api.movimentacao_estoque.dto.MovimentacaoEstoqueRequestDTO;
import br.com.elementosearte.elementosearte_api.movimentacao_estoque.dto.MovimentacaoEstoqueResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacao-estoque")
public class MovimentacaoEstoqueController {

    private final MovimentacaoEstoqueService movimentacaoEstoqueService;


    public MovimentacaoEstoqueController(MovimentacaoEstoqueService movimentacaoEstoqueService) {
        this.movimentacaoEstoqueService = movimentacaoEstoqueService;
    }

    @PostMapping
    public ResponseEntity<MovimentacaoEstoqueResponseDTO> registrarMovimentacao(@RequestBody @Valid MovimentacaoEstoqueRequestDTO movimentacaoEstoqueRequestDTO) {
        MovimentacaoEstoqueResponseDTO movimentacaoEstoqueResponseDTO = movimentacaoEstoqueService.registrarMovimentacao(movimentacaoEstoqueRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(movimentacaoEstoqueResponseDTO);
    }

    @GetMapping
    ResponseEntity<List<MovimentacaoEstoqueResponseDTO>> listarMovimentacoes() {
        List<MovimentacaoEstoqueResponseDTO> listarMovimentacoes = movimentacaoEstoqueService.listarMovimentacoes();
        return ResponseEntity.ok(listarMovimentacoes);
    }

    @GetMapping("/buscarPorId/{idMovimentacaoEstoque}")
    ResponseEntity<MovimentacaoEstoqueResponseDTO> buscarMovimentacaoPorId(@PathVariable Long idMovimentacaoEstoque) {
        MovimentacaoEstoqueResponseDTO movimentacaoEstoqueResponseDTO =
                movimentacaoEstoqueService.buscarMovimentacaoPorId(idMovimentacaoEstoque);
        return ResponseEntity.ok(movimentacaoEstoqueResponseDTO);
    }

    @GetMapping("/tipo/{tipoMovimentacao}")
    public ResponseEntity<List<MovimentacaoEstoqueResponseDTO>> listarPorTipoMovimentacao(@PathVariable TipoMovimentacao tipoMovimentacao) {
        return ResponseEntity.ok(movimentacaoEstoqueService.listarPorTipoMovimentacao(tipoMovimentacao));
    }

    @GetMapping("/listarMovimentacoesPorProduto/{idProduto}")
    ResponseEntity<List<MovimentacaoEstoqueResponseDTO>> listarMovimentacoesPorProduto(@PathVariable Long idProduto) {
        List<MovimentacaoEstoqueResponseDTO> movimentacaoEstoqueResponseDTO =
                movimentacaoEstoqueService.listarMovimentacoesPorProduto(idProduto);
        return ResponseEntity.ok(movimentacaoEstoqueResponseDTO);

    }

    @GetMapping("/listarMovimentacoesPorUsuario/{idUsuario}")
    ResponseEntity<List<MovimentacaoEstoqueResponseDTO>> listarMovimentacoesPorUsuario(@PathVariable Long idUsuario) {
        List<MovimentacaoEstoqueResponseDTO> movimentacaoEstoqueResponseDTO =
                movimentacaoEstoqueService.listarMovimenacoesPorUsuario(idUsuario);
        return ResponseEntity.ok(movimentacaoEstoqueResponseDTO);
    }


}
