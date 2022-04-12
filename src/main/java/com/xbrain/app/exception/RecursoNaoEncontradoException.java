package com.xbrain.app.exception;

public abstract class RecursoNaoEncontradoException extends RuntimeException {

    protected RecursoNaoEncontradoException(String message) {
        super(message);
    }
}
