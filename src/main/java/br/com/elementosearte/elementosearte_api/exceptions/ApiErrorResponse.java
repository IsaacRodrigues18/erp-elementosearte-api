package br.com.elementosearte.elementosearte_api.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiErrorResponse {

    private int status;
    private String erro;
    private String mensagem;
    private String path;


}
