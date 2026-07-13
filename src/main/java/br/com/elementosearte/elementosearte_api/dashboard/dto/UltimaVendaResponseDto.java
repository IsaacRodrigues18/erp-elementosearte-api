package br.com.elementosearte.elementosearte_api.dashboard.dto;

import br.com.elementosearte.elementosearte_api.venda.StatusVenda;
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
public class UltimaVendaResponseDto {
    private Long idVenda;
    private String nomeUsuario;
    private BigDecimal valorTotal;
    private LocalDateTime criadoEm;
    private StatusVenda statusVenda;
}
