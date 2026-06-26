package br.com.elementosearte.elementosearte_api.produto_fornecedor;

import br.com.elementosearte.elementosearte_api.fornecedor.FornecedorEntity;
import br.com.elementosearte.elementosearte_api.produtos.ProdutoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_produto_fornecedor")
public class ProdutoFornecedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto_fornecedor")
    private Long idProdutoFornecedor;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private ProdutoEntity produto;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor", nullable = false)
    private FornecedorEntity fornecedor;


    @Column(name = "fornecedor_principal", nullable = false)
    private boolean fornecedorPrincipal = false;

    @Column(name = "custo_fornecedor", nullable = false, precision = 10, scale = 2)
    private BigDecimal custoFornecedor;

    @Column(name = "ativo", nullable = false)
    private boolean ativo = true;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;

    @PrePersist
    public void prePersist() {
        LocalDateTime agora = LocalDateTime.now();
        this.criadoEm = agora;
        this.atualizadoEm = agora;
    }

    @PreUpdate
    public void preUpdate() {
        this.atualizadoEm = LocalDateTime.now();
    }

}
