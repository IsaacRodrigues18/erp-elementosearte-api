    package br.com.elementosearte.elementosearte_api.movimentacao_estoque;

    import br.com.elementosearte.elementosearte_api.produtos.ProdutoEntity;
    import br.com.elementosearte.elementosearte_api.usuarios.UsuarioEntity;
    import jakarta.persistence.*;
    import jakarta.validation.constraints.NotNull;
    import jakarta.validation.constraints.Positive;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.math.BigDecimal;
    import java.time.LocalDateTime;

    @Getter
    @Setter
    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "tb_movimentacao_estoque")
    public class MovimentacaoEstoqueEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_movimentacao_estoque")
        private Long idMovimentacaoEstoque;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_produto", nullable = false)
        private ProdutoEntity produto;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_usuario", nullable = false)
        private UsuarioEntity usuario;

        @NotNull
        @Positive
        @Column(name = "quantidade", nullable = false)
        private Integer quantidade;

        @NotNull
        @Column(name = "valor_unitario", nullable = false)
        private BigDecimal valorUnitario;

        @Enumerated(EnumType.STRING)
        @Column(name = "tipo_movimentacao", nullable = false)
        private TipoMovimentacao tipoMovimentacao;

        @Column(name = "observacao")
        private String observacao;

        @Column(name = "criado_em", nullable = false)
        private LocalDateTime criadoEm;

        @PrePersist
        public void prePersist() {
            this.criadoEm = LocalDateTime.now();
        }




    }
