package br.com.elementosearte.elementosearte_api.produtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_produtos")
public class ProdutoEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long id_produtos;

    @NotBlank
    @Column(name = "nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "descricao", length = 1000)
    private String descricao;

    @NotBlank
    @Column(name = "custo_referencia", nullable = false)
    private float custoReferencia;

    @NotBlank
    @Column(name = "preco_venda_referencia", nullable = false)
    private float precoVendaReferencia;


    @Column(name = "ativo", nullable = false)
    private boolean ativo = true;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;


    @PrePersist void prePersist(){
        this.criadoEm = LocalDateTime.now();
        this.atualizadoEm = LocalDateTime.now();
    }


    @PreUpdate
    public void preUpdate() {
        this.atualizadoEm = LocalDateTime.now();
    }








}
