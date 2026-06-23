package br.com.elementosearte.elementosearte_api.usuarios;

import br.com.elementosearte.elementosearte_api.usuarios.dto.UsuarioRequestDTO;
import br.com.elementosearte.elementosearte_api.usuarios.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {


    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioResponseDTO usuario = usuarioService.cadastrarUsuario(usuarioRequestDTO);
        return ResponseEntity.status(201).body(usuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodosUsuarios(){
        List<UsuarioResponseDTO> usuario = usuarioService.listarTodosUsuarios();
        return ResponseEntity.ok(usuario);
    }
    @GetMapping("/listar-usuarios-ativos")
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuariosAtivos(){
        List<UsuarioResponseDTO> usuarios = usuarioService.listarUsuariosAtivos();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/buscar-por-email")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorEmail(@RequestParam String email){
        UsuarioResponseDTO usuario = usuarioService.buscarUsuarioPorEmail(email);
        return ResponseEntity.ok(usuario);
    }
    @PatchMapping("/desativar/{idUsuario}")
    public ResponseEntity<UsuarioResponseDTO> desativarUsuario(@PathVariable Long idUsuario) {
        UsuarioResponseDTO usuarioDesativado = usuarioService.desativarUsuario(idUsuario);
        return ResponseEntity.ok(usuarioDesativado);
    }

    @PatchMapping("/reativar/{idUsuario}")
    public ResponseEntity<UsuarioResponseDTO> reativarUsuario(@PathVariable Long idUsuario) {
        UsuarioResponseDTO usuarioReativado = usuarioService.reativarUsuario(idUsuario);
        return ResponseEntity.ok(usuarioReativado);
    }


    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long idUsuario) {
        usuarioService.deletarUsuario(idUsuario);
        return ResponseEntity.noContent().build();
    }

}

