package br.com.elementosearte.elementosearte_api.produtos;

import br.com.elementosearte.elementosearte_api.categorias.CategoriaEntity;
import br.com.elementosearte.elementosearte_api.usuarios.UsuarioEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
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
    private Long idProduto;

    @NotBlank
    @Column(name = "nome_produto", nullable = false, length = 255)
    private String nomeProduto;

    @Column(name = "descricao_produto", length = 255)
    private String descricaoProduto;

    @NotNull
    @DecimalMin(value = "0.00")
    @Column(name = "custo_referencia", nullable = false, precision = 10, scale = 2)
    private BigDecimal custoReferencia;

    @NotNull
    @DecimalMin(value = "0.00")
    @Column(name = "preco_venda_referencia", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoVendaReferencia;

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

    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria", nullable = false)
    private CategoriaEntity categoria;

    @ManyToOne
    @JoinColumn(name = "criado_por_usuario_id", referencedColumnName = "id_usuario")
    private UsuarioEntity criadoPorUsuario;

    @ManyToOne
    @JoinColumn(name = "atualizado_por_usuario_id", referencedColumnName = "id_usuario")
    private UsuarioEntity atualizadoPorUsuario;


}