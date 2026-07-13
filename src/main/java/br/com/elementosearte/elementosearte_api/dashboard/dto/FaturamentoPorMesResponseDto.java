package br.com.elementosearte.elementosearte_api.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FaturamentoPorMesResponseDto {

    private Integer mes;
    private BigDecimal faturamentomensal;



}
