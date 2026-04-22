package br.com.elementosearte.elementosearte_api.usuarios;

import br.com.elementosearte.elementosearte_api.usuarios.dto.UsuarioResponseDTO;
import br.com.elementosearte.elementosearte_api.usuarios.dto.UsuarioRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO usuarioDto) {
        if (usuarioRepository.existsByEmail(usuarioDto.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome(usuarioDto.getNome());
        usuarioEntity.setEmail(usuarioDto.getEmail());
        usuarioEntity.setSenha(usuarioDto.getSenha());

        UsuarioEntity usuarioSalvo = usuarioRepository.save(usuarioEntity);

        return new UsuarioResponseDTO(
                usuarioSalvo.getId_usuario(),
                usuarioSalvo.getNome(),
                usuarioSalvo.getEmail(),
                usuarioSalvo.isAtivo()
        );
    }


    public void deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        usuarioRepository.deleteById(id);
    }
}