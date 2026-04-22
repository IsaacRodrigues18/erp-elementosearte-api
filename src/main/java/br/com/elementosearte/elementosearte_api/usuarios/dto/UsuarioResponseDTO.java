package br.com.elementosearte.elementosearte_api.usuarios.dto;

public class UsuarioResponseDTO {

    private Long id_usuario;
    private String nome;
    private String email;
    private boolean ativo;

    public UsuarioResponseDTO() {
    }
    public UsuarioResponseDTO(Long id_usuario, String nome, String email, boolean ativo){
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.email = email;
        this.ativo = ativo;

    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
