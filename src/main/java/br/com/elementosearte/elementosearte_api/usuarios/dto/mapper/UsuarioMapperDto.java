package br.com.elementosearte.elementosearte_api.usuarios.dto.mapper;

import br.com.elementosearte.elementosearte_api.usuarios.UsuarioEntity;
import br.com.elementosearte.elementosearte_api.usuarios.dto.UsuarioRequestDTO;
import br.com.elementosearte.elementosearte_api.usuarios.dto.UsuarioResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapperDto {


    public UsuarioResponseDTO toResponseDto(UsuarioEntity usuarioEntity) {
        return new  UsuarioResponseDTO(
                usuarioEntity.getIdUsuario(),
                usuarioEntity.getNome(),
                usuarioEntity.getEmail(),
                usuarioEntity.isAtivo()
        );

    }

    public UsuarioEntity toEntityDto(UsuarioRequestDTO usuarioRequestDTO){
        UsuarioEntity usuario = new UsuarioEntity();

        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setSenha(usuarioRequestDTO.getSenha());

        return usuario;

    }
}
