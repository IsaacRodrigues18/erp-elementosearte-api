package br.com.elementosearte.elementosearte_api.usuarios;

import br.com.elementosearte.elementosearte_api.exceptions.BusinessException;
import br.com.elementosearte.elementosearte_api.exceptions.ResourceNotFoundException;
import br.com.elementosearte.elementosearte_api.usuarios.dto.UsuarioRequestDTO;
import br.com.elementosearte.elementosearte_api.usuarios.dto.UsuarioResponseDTO;
import br.com.elementosearte.elementosearte_api.usuarios.dto.mapper.UsuarioMapperDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapperDto usuarioMapperDto;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapperDto usuarioMapperDto) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapperDto = usuarioMapperDto;
    }

    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        if (usuarioRepository.existsByEmail(usuarioRequestDTO.getEmail())) {
            throw new BusinessException("Email já cadastrado");
        }

        UsuarioEntity usuario = usuarioMapperDto.toEntityDto(usuarioRequestDTO);
        UsuarioEntity usuarioSalvo = usuarioRepository.save(usuario);

        return usuarioMapperDto.toResponseDto(usuarioSalvo);
    }

    public List<UsuarioResponseDTO> listarTodosUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapperDto::toResponseDto)
                .toList();
    }

    public List<UsuarioResponseDTO> listarUsuariosAtivos() {
        return usuarioRepository.findByAtivoTrue()
                .stream()
                .map(usuarioMapperDto::toResponseDto)
                .toList();
    }

    public UsuarioResponseDTO buscarUsuarioPorEmail(String email) {
        UsuarioEntity usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        return usuarioMapperDto.toResponseDto(usuario);
    }

    public UsuarioResponseDTO desativarUsuario(Long idUsuario) {
        UsuarioEntity usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        if (!usuario.isAtivo()) {
            throw new BusinessException("Usuário já está desativado");
        }

        usuario.setAtivo(false);

        UsuarioEntity usuarioDesativado = usuarioRepository.save(usuario);
        return usuarioMapperDto.toResponseDto(usuarioDesativado);
    }

    public UsuarioResponseDTO reativarUsuario(Long idUsuario) {
        UsuarioEntity usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        if (usuario.isAtivo()) {
            throw new BusinessException("Usuário já está ativo");
        }

        usuario.setAtivo(true);

        UsuarioEntity usuarioAtivado = usuarioRepository.save(usuario);
        return usuarioMapperDto.toResponseDto(usuarioAtivado);
    }

    public void deletarUsuario(Long idUsuario) {
        if (!usuarioRepository.existsById(idUsuario)) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        }

        usuarioRepository.deleteById(idUsuario);
    }
}