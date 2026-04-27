    package br.com.elementosearte.elementosearte_api.usuarios;

    import br.com.elementosearte.elementosearte_api.produtos.ProdutoEntity;
    import jakarta.persistence.*;
    import jakarta.validation.constraints.Email;
    import jakarta.validation.constraints.NotBlank;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.time.LocalDateTime;
    import java.util.List;

    @Entity
    @Table(name = "tb_usuarios")
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public class UsuarioEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_usuario")
        private Long idUsuario;

        @NotBlank
        @Column(name = "nome", nullable = false, length = 100)
        private String nome;

        @NotBlank
        @Email
        @Column(name = "email", nullable = false, unique = true, length = 255)
        private String email;

        @NotBlank
        @Column(name = "senha", nullable = false, length = 255)
        private String senha;

        @Column(name = "ativo", nullable = false)
        private boolean ativo = true;

        @Column(name = "criado_em", nullable = false)
        private LocalDateTime criadoEm;

        @Column(name = "atualizado_em", nullable = false)
        private LocalDateTime atualizadoEm;

        @PrePersist
        public void prePersist() {
            this.criadoEm = LocalDateTime.now();
            this.atualizadoEm = LocalDateTime.now();
        }

        @PreUpdate
        public void preUpdate() {
            this.atualizadoEm = LocalDateTime.now();
        }

        @OneToMany(mappedBy = "criadoPorUsuario")
        private List<ProdutoEntity> produtosCriados;

        @OneToMany(mappedBy = "atualizadoPorUsuario")
        private List<ProdutoEntity> produtosAtualizados;


    }


