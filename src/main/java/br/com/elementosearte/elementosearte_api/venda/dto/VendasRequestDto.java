package br.com.elementosearte.elementosearte_api.venda.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VendasRequestDto {

    @NotBlank(message = "Forma de pagamento não pode ser nula")
    private String formaPagamento;

    @NotNull(message = "O valor total deve ser informado")
    private BigDecimal valorTotal;

    @Valid
    @NotEmpty(message = "A venda deve possuir pelo menos um item")
    private List<VendasItemRequestDto> itens;



}
