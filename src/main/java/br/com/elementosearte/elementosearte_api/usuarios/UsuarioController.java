package br.com.elementosearte.elementosearte_api.usuarios;

import br.com.elementosearte.elementosearte_api.usuarios.dto.UsuarioRequestDTO;
import br.com.elementosearte.elementosearte_api.usuarios.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/elementos-e-arte/usuarios")
public class UsuarioController {


    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@Valid @RequestBody UsuarioRequestDTO dto) {

        UsuarioResponseDTO usuario = usuarioService.cadastrarUsuario(dto);

        return ResponseEntity.status(201).body(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        usuarioService.deletarUsuario(id);

        return ResponseEntity.noContent().build();
    }


}

