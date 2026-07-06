package br.com.elementosearte.elementosearte_api.venda.dto;
import br.com.elementosearte.elementosearte_api.venda.TipoDesconto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VendasItemRequestDto {

    @NotNull(message = "O produto deve ser informado")
    private Long idProduto;

    @Positive(message = " A quantidade deve ser maior que zero")
    private  Integer quantidade;

    @NotNull(message = "O valor unitário deve ser informado")
    private BigDecimal valorUnitario;

    private TipoDesconto tipoDesconto;

    private  BigDecimal valorDesconto;

    @NotNull(message = "O sub total deve ser informado")
    private BigDecimal subTotal;

}
