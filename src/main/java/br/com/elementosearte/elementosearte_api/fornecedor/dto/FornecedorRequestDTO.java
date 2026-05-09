package br.com.elementosearte.elementosearte_api.fornecedor.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorRequestDTO {

    @NotBlank
    private String nomeFornecedor;
    @NotBlank
    private String cidade;

    private String telefone;

    @Email
    private String email;

    private String observacao;
}
