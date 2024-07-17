package com.example.forumhub.domain.exceptions;

public class UsuarioNaoEncontrado extends RuntimeException{
    public UsuarioNaoEncontrado(String message) {
        super(message);
    }
}
