package br.com.elementosearte.elementosearte_api.usuarios.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioResponseDTO {

    private Long id_usuario;
    private String nome;
    private String email;
    private boolean ativo;


}
