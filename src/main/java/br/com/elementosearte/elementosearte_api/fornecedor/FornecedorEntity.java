package br.com.elementosearte.elementosearte_api.fornecedor;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_fornecedores")
public class FornecedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fornecedor")
    private Long idFornecedor;

    @NotBlank
    @Column(name = "nome_fornecedor", nullable = false, length = 100)
    private String nomeFornecedor;

    @NotBlank
    @Column(name = "cidade", nullable = false, length = 100)
    private String cidade;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Email
    @Column(name = "email", unique = true, length = 255)
    private String email;

    @Column(name = "observacao", length = 255)
    private String observacao;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

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


}


