package br.com.elementosearte.elementosearte_api.fornecedor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorResponseDTO {

    private Long idFornecedor;

    private String nomeFornecedor;

    private String cidade;

    private String telefone;

    private String email;

    private String observacao;

    private boolean ativo;
}
