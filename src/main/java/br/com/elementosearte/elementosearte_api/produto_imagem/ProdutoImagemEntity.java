package br.com.elementosearte.elementosearte_api.produto_imagem;

import br.com.elementosearte.elementosearte_api.produtos.ProdutoEntity;
import jakarta.persistence.*;
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
@Table(name = "tb_produto_imagem")
public class ProdutoImagemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto_imagem")
    private Long idProdutoImagem;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private ProdutoEntity produto;

    @NotBlank
    @Column(name = "url_imagem", nullable = false, length = 255)
    private String urlImagem;


    @NotBlank
    @Column(name = "nome_arquivo", nullable = false,length = 255)
    private String nomeArquivo;

    @Column(name = "principal", nullable = false)
    private boolean principal = false;

    @Column(name = "ativo", nullable = false)
    private boolean ativo = true;

    @Column(name = "ordem_exibicao", nullable = false)
    private Integer ordemExibicao;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;

    @PrePersist
    void prePersist() {
        this.criadoEm = LocalDateTime.now();
        this.atualizadoEm = LocalDateTime.now();

        if (this.ordemExibicao == null) {
            this.ordemExibicao = 0;
        }
    }


    @PreUpdate void preUpdate() {
        this.atualizadoEm = LocalDateTime.now();
    }
}
