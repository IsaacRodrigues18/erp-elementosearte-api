package br.com.elementosearte.elementosearte_api.venda;

import br.com.elementosearte.elementosearte_api.produtos.ProdutoEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_vendas_item")
public class VendaItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venda_item")
    private Long idVendaItem;

    @ManyToOne
    @JoinColumn(name = "id_venda", nullable = false)
    private VendasEntity venda;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private ProdutoEntity produto;

    @Positive
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @NotNull
    @Column(name = "valor_unitario", nullable = false)
    private BigDecimal valorUnitario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_desconto", length = 20)
    private TipoDesconto tipoDesconto;

    @Column(name = "valor_desconto")
    private BigDecimal valorDesconto;

    @NotNull
    @Column(name = "sub_total", nullable = false)
    private BigDecimal subTotal;
}