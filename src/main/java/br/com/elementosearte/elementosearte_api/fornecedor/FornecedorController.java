package br.com.elementosearte.elementosearte_api.fornecedor;

import br.com.elementosearte.elementosearte_api.fornecedor.dto.FornecedorRequestDTO;
import br.com.elementosearte.elementosearte_api.fornecedor.dto.FornecedorResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @PostMapping
    public ResponseEntity<FornecedorResponseDTO> criarFornecedor(
            @RequestBody @Valid FornecedorRequestDTO fornecedorRequestDTO) {

        FornecedorResponseDTO fornecedorResponseDTO =
                fornecedorService.criarFornecedor(fornecedorRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<FornecedorResponseDTO>> listarFornecedores() {
        return ResponseEntity.ok(fornecedorService.listarFornecedoresAtivos());
    }

    @GetMapping("/cidade/{cidade}")
    public ResponseEntity<List<FornecedorResponseDTO>> listarFornecedoresPorCidade(
            @PathVariable String cidade) {

        return ResponseEntity.ok(fornecedorService.listarFornecedoresPorCidade(cidade));
    }

    @GetMapping("/{idFornecedor}")
    public ResponseEntity<FornecedorResponseDTO> buscarPorId(
            @PathVariable Long idFornecedor) {

        return ResponseEntity.ok(fornecedorService.buscarFornecedorPorId(idFornecedor));
    }

    @PatchMapping("/{idFornecedor}/inativar")
    public ResponseEntity<Void> inativarFornecedor(@PathVariable Long idFornecedor) {
        fornecedorService.inativarFornecedor(idFornecedor);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{idFornecedor}/ativar")
    public ResponseEntity<Void> ativarFornecedor(@PathVariable Long idFornecedor) {
        fornecedorService.ativarFornecedor(idFornecedor);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idFornecedor}")
    public ResponseEntity<Void> deletarFornecedor(@PathVariable Long idFornecedor) {
        fornecedorService.deletarFornecedor(idFornecedor);
        return ResponseEntity.noContent().build();
    }
}