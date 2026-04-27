package br.com.elementosearte.elementosearte_api.categorias;

import br.com.elementosearte.elementosearte_api.categorias.dto.CategoriaRequestDTO;
import br.com.elementosearte.elementosearte_api.categorias.dto.CategoriaResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> criarCategoria(@RequestBody @Valid CategoriaRequestDTO categoriaDTO) {
        CategoriaResponseDTO categoriaCriada = categoriaService.criarCategoria(categoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCriada);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listarCategorias() {
        List<CategoriaResponseDTO> categorias = categoriaService.listarCategorias();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<CategoriaResponseDTO> buscarCategoriaPorId(@PathVariable Long idCategoria) {
        CategoriaResponseDTO categoria = categoriaService.buscarCategoriaPorId(idCategoria);
        return ResponseEntity.ok(categoria);
    }

    @PutMapping("/{idCategoria}")
    public ResponseEntity<CategoriaResponseDTO> atualizarCategoria(
            @PathVariable Long idCategoria,
            @RequestBody @Valid CategoriaRequestDTO categoriaDTO
    ) {
        CategoriaResponseDTO categoriaAtualizada = categoriaService.atualizarCategoria(idCategoria, categoriaDTO);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long idCategoria) {
        categoriaService.deletarCategoria(idCategoria);
        return ResponseEntity.noContent().build();
    }
}
