package br.com.elementosearte.elementosearte_api.venda.dto;

import br.com.elementosearte.elementosearte_api.produtos.ProdutoEntity;
import br.com.elementosearte.elementosearte_api.venda.TipoDesconto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VendasItensResponseDto {

        private Long idVendaItem;

        private Long idVenda;

        private Long idProduto;

        private String nomeProduto;

        private Integer quantidade;

        private BigDecimal valorUnitario;

        private TipoDesconto tipoDesconto;

        private BigDecimal valorDesconto;

        private BigDecimal subTotal;

}
