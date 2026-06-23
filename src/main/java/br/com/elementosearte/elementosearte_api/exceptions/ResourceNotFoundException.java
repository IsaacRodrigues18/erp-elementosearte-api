package br.com.elementosearte.elementosearte_api.exceptions;

public class ResourceNotFoundException extends  RuntimeException{

    public ResourceNotFoundException(String mensagem) {
        super(mensagem);
    }
}

