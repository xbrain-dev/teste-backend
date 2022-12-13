package com.xbrain.app.exception;


public class VendedorNaoEncontradoException extends RecursoNaoEncontradoException {

        public VendedorNaoEncontradoException() {
            super("Vendedor não encontrado!");
        }
}
