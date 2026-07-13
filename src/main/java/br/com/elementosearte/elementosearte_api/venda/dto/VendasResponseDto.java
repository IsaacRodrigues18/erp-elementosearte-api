package br.com.elementosearte.elementosearte_api.venda.dto;

import br.com.elementosearte.elementosearte_api.usuarios.UsuarioEntity;
import br.com.elementosearte.elementosearte_api.venda.FormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VendasResponseDto {

    private Long idVenda;

    private Long idUsuario;

    private String nomeUsuario;

    private FormaPagamento formaPagamento;

    private BigDecimal valorTotal;
}
