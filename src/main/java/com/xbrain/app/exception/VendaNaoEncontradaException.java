package com.xbrain.app.exception;

public class VendaNaoEncontradaException extends RecursoNaoEncontradoException {

    public VendaNaoEncontradaException() {
        super("Venda não encontrada!");
    }
}
